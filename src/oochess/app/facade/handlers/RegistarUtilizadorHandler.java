package oochess.app.facade.handlers;

import java.lang.reflect.InvocationTargetException;
import oochess.app.OOChessPreferences;
import oochess.app.domain.CatJogadores;
import oochess.app.domain.Jogador;
import oochess.app.elostrategies.EloCalculator;
import oochess.app.facade.exceptions.VarianteEloInexistente;

/**
 * This class is responsible for registering a new user into
 * the system
 * 
 * @author João Anjos fc54476
 */
public class RegistarUtilizadorHandler {
	
	private CatJogadores jogadores;
	
	/**
	 * The constructor for the class
	 * 
	 * @param jogadores - the system's user catalog
	 */
	public RegistarUtilizadorHandler(CatJogadores jogadores) {
		this.jogadores = jogadores;
	}
	
	/**
	 * Regista um utilizador normal.
	 * @param discordUsername
	 * @param Username
	 * @param Password
	 * @throws VarianteEloInexistente if the system doesn't support the specified elo strategy
	 * @ensures existe um utilizador com esse username
	 */
	public void registarUtilizador(String username, String password, String discordUsername) throws VarianteEloInexistente {
		jogadores.addJogador(new Jogador(username, discordUsername, password, getDefaultElo()));
	}
	
	/**
	 * Gets the default elo for the specified elo strategy in properties
	 * 
	 * @return the default Elo for a new player
	 * @throws VarianteEloInexistente if the system doesn't support the specified elo strategy
	 */
	public double getDefaultElo() throws VarianteEloInexistente {
		String eloStrat = OOChessPreferences.getInstance().loadProperty("ELO_STRATEGY");
		try {
			@SuppressWarnings("unchecked")
			Class<EloCalculator> eloCalcClass= (Class<EloCalculator>) Class.forName(eloStrat);
			return eloCalcClass.getConstructor().newInstance().defaultElo();
		} catch (ClassNotFoundException e) {
			throw new VarianteEloInexistente("A variante de elo escolhida não existe no programa", e);
		} catch (NoSuchMethodException | SecurityException | InstantiationException 
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		} 
		return 0;
	}

}
