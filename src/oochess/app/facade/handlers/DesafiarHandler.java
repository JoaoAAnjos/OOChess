package oochess.app.facade.handlers;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.List;

import oochess.app.OOChessPreferences;
import oochess.app.discordintegration.DiscordSender;
import oochess.app.domain.CatJogadores;
import oochess.app.domain.CatTorneios;
import oochess.app.domain.Jogador;
import oochess.app.domain.Torneio;
import oochess.app.facade.dto.JogadorEElo;
import oochess.app.facade.exceptions.BibliotecaNaoSuportada;
import oochess.app.facade.exceptions.TorneioInexistente;
import oochess.app.facade.exceptions.UtilizadorInexistente;

/**
 * This class is responsible for creating challenges between
 * users
 * 
 * @author João Anjos fc54476
 */
public class DesafiarHandler {
	
	private Jogador autenticado;
	private Jogador desafiado;
	private CatJogadores jogadores;
	private CatTorneios torneios;
	private Torneio torneioDesafio = null;
	
	/**
	 * The constructor for the class
	 * 
	 * @param autenticado - the authenticated user
	 * @param jogadores - the system player catalog
	 * @param torneios - the system tournament catalog
	 */
	public DesafiarHandler(Jogador autenticado, CatJogadores jogadores, CatTorneios torneios) {
		this.autenticado = autenticado;
		this.jogadores = jogadores;
		this.torneios = torneios;
	}

	/**
	 * Indicates the tournament that the challenge's match will belong to
	 * 
	 * @param nome - the tournament name
	 * @requires an authenticated user to exist
	 * @ensures that a tournament with this name exists
	 * @throws TorneioInexistente if the given tournament doesn't exist
	 */
	public void indicaTorneio(String nome) throws TorneioInexistente {
		torneioDesafio = torneios.getTorneio(nome).orElseThrow(() -> new TorneioInexistente("O torneio indicado não existe"));
	}

	/**
	 * Calculates the players whose absolute difference between their elo and the user's elo is
	 * lower than delta
	 * 
	 * @param delta - acceptable deltaElo
	 * @requires an authenticated user to exist
	 * @ensures that the difference between the user's elo and each player's elo in the list
	 * are below delta
	 * @return a list with the players and their respective elos
	 */
	public List<JogadorEElo> indicaDeltaElo(int delta) {
		return jogadores.newChallengeList(delta, autenticado);
	}

	/**
	 * Indicates to the system which player the user wants to challenge
	 * 
	 * @param nome - the player's username
	 * @requires an authenticated user to exist
	 * @ensures that a player with this name exists
	 * @throws UtilizadorInexistente if the given user does not exist
	 */
	public void indicaJogador(String nome) throws UtilizadorInexistente {
		desafiado = jogadores.getJogador(nome).orElseThrow(() -> new UtilizadorInexistente("Não existe este utilizador"));
	}

	/**
	 * Indicates to the system the date of the challenge, and the message
	 * to be sent to the challenged player through Discord, if any, and gets
	 * the challenge's code
	 * 
	 * @param datahora - the date
	 * @param msg - the message to be sent
	 * @return the challenge's code
	 * @throws BibliotecaNaoSuportada if the system doesn't support the specified Discord API
	 */
	public String indicaDetalhes(LocalDateTime datahora, String msg) throws BibliotecaNaoSuportada {
		autenticado.addDesafio(autenticado, desafiado, torneioDesafio, datahora);
		msg = "O Jogador " + autenticado+ " convidou-o para uma partida de xadrez em "+ datahora +":" + msg;
		sendDiscordMessage(msg);
		return desafiado.addDesafio(autenticado, desafiado, torneioDesafio, datahora); 
	}

	/**
	 * Responsible for sending the discord message through the specified Discord API
	 * 
	 * @param msg - the message
	 * @requires a challenged player to exist
	 * @throws BibliotecaNaoSuportada if the system doesn't support the specified Discord API
	 */
	private void sendDiscordMessage(String msg) throws BibliotecaNaoSuportada {
		OOChessPreferences properties = OOChessPreferences.getInstance();
		String senderClassName = properties.loadProperty("DISCORD_CLASS");
		String token = properties.loadProperty("DISCORD_TOKEN");
		try {
			@SuppressWarnings("unchecked")
			Class<DiscordSender> senderClass = (Class<DiscordSender>) Class.forName(senderClassName);
			senderClass.getConstructor(String.class).newInstance(token).sendMessage(desafiado.getDiscordUsername(), msg);
		} catch (ClassNotFoundException e) {
			throw new BibliotecaNaoSuportada("O programa não suporta a biblioteca de Discord indicada", e);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException 
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}
	
}
