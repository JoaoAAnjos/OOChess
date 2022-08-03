package oochess.app.facade.handlers;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.List;

import oochess.app.OOChessPreferences;
import oochess.app.domain.CatJogadores;
import oochess.app.domain.Desafio;
import oochess.app.domain.Jogador;
import oochess.app.elostrategies.EloCalculator;
import oochess.app.facade.exceptions.DesafioInvalido;
import oochess.app.facade.exceptions.UtilizadorInexistente;
import oochess.app.facade.exceptions.VarianteEloInexistente;

/**
 * This class is responsible for registering the result
 * of a match, either a spontaneous or a challenged match
 * 
 * (This class  is responsible for the creation of matches from a challenge 
 * due to the lack of implementation of the ProcessarDesafiosHandler class)
 * 
 * @author João Anjos fc54476
 */
public class RegistarResultadoHandler {
	
	private CatJogadores jogadores;
	private Jogador autenticado;
	private Jogador oponente;
	private LocalDateTime datahora;
	private Desafio desafio = null;
	

	/**
	 * The constructor for the class
	 * 
	 * @param autenticado - the authenticated user
	 * @param jogadores - the system players
	 */
	public RegistarResultadoHandler(Jogador autenticado, CatJogadores jogadores) {
		this.autenticado = autenticado;
		this.jogadores = jogadores;
	}

	/**
	 * Indicates the challenge associated to the match, if there is any
	 * 
	 * @param codigoDesafio - the challenge's code
	 * @requires an authenticated user to exist
	 * @ensures that a challenge with this code involving the user exists 
	 * @throws DesafioInvalido if the given challenge doesn't exist
	 */
	public void indicaDesafio(String codigoDesafio) throws DesafioInvalido{
		desafio = autenticado.getDesafio(codigoDesafio).orElseThrow(() -> new DesafioInvalido("Código de desafio inválido"));
	}

	/**
	 * Indicates that its registering the result for a spontaneous match
	 * and returns the user's last five opponents
	 * 
	 * @requires an authenticated user to exist
	 * @return a list with the user's last five opponents usernames
	 */
	public List<String> indicaPartidaEspontanea() {
		return autenticado.ultimos5Oponentes();
	}
	
	/**
	 * Indicates the opponent's username and date of the match, in case
	 * a spontaneous match is being registered
	 * 
	 * @param username - the opponents username
	 * @param datahora - the match's date
	 * @ensures that a user with this username exists
	 * @throws UtilizadorInexistente if the given player doesn't exist
	 */
	public void indicaDetalhes(String username, LocalDateTime datahora) throws UtilizadorInexistente{
		oponente = jogadores.getJogador(username).orElseThrow(() -> new UtilizadorInexistente("Não existe este utilizador"));
		this.datahora = datahora;
	}

	/**
	 * Indicates the match result and returns the user's updated elo
	 * 
	 * @param resultado - the user's match result
	 * @requires an authenticated user and an opponent player to exist
	 * @ensures that a new Match is created, added to both players personal match
	 * list with the correct result, and their elos are updated
	 * @return the user's updated elo based on the result and elo strategy
	 * @throws VarianteEloInexistente if the system doesn't support the specified elo strategy
	 */
	public double indicarResultado(String resultado) throws VarianteEloInexistente {
		String resultadoOponente = getOpponentResult(resultado);
		if(desafio != null) {
			oponente = desafio.getOtherJogador(autenticado);
			autenticado.addPartida(desafio.createPartida(autenticado, oponente, resultado));
			oponente.addPartida(desafio.createPartida(oponente, autenticado, resultadoOponente));
		} else {
			autenticado.addPartida(autenticado.newPartida(autenticado, oponente, resultado, datahora));
			oponente.addPartida(oponente.newPartida(oponente, autenticado, resultadoOponente, datahora));
		}
		calculateElo(resultado);
		return autenticado.getElo();
	}
	
	/**
	 * Gets the opponent match result based on the user's match result
	 * 
	 * @param resultado - the user's match result
	 * @return the opponent match result
	 */
	private String getOpponentResult(String resultado) {
		String result = "";
		if(resultado.contentEquals("VITORIA")) {
			result = "DERROTA";
		} else if(resultado.contentEquals("DERROTA")) {
			result = "VITORIA";
		}else if(resultado.contentEquals("EMPATE")) {
			result = "EMPATE";
		}
		return result;
	}

	/**
	 * Calculates the new elo for both players
	 * 
	 * @param resultado - the user's match result
	 * @requires an authenticated user and an opponent to exist
	 * @throws VarianteEloInexistente if the system doesn't support the specified elo strategy
	 */
	private void calculateElo(String resultado) throws VarianteEloInexistente {
		String eloCalcStrat = OOChessPreferences.getInstance().loadProperty("ELO_STRATEGY");
		try {
			@SuppressWarnings("unchecked")
			Class<EloCalculator> eloCalcClass = (Class<EloCalculator>) Class.forName(eloCalcStrat);
			double[] newElos = eloCalcClass.getConstructor().newInstance().calculateElo(autenticado.getElo(), oponente.getElo(), resultado);
			autenticado.changeElo(newElos[0]);
			oponente.changeElo(newElos[1]);
		} catch (ClassNotFoundException e) {
			throw new VarianteEloInexistente("A variante de elo escolhida não existe no programa", e);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException 
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} 
	}
	
}
