package oochess.app.facade;

import oochess.app.domain.CatJogadores;
import oochess.app.domain.CatTorneios;
import oochess.app.domain.Jogador;
import oochess.app.facade.handlers.DesafiarHandler;
import oochess.app.facade.handlers.ProcessarDesafiosHandler;
import oochess.app.facade.handlers.RegistarResultadoHandler;

/**
 * This class represents a session
 * of an authenticated user
 */
public class Sessao {
	
	private Jogador autenticado;
	private CatJogadores jogadores;
	private CatTorneios torneios;
	
	/**
	 * @param jogador - the authenticated user
	 * @param jogadores - the system's user catalog
	 * @param torneios - the system's tournament catalog
	 */
	public Sessao(Jogador jogador, CatJogadores jogadores, CatTorneios torneios) {
		autenticado = jogador;
		this.jogadores = jogadores;
		this.torneios = torneios;
	}
	
	public DesafiarHandler getDesafioParaPartidaHandler() {
		return new DesafiarHandler(autenticado, jogadores, torneios);
	}

	public RegistarResultadoHandler getRegistarResultadoDePartida() {
		return new RegistarResultadoHandler(autenticado, jogadores);
	}

	public ProcessarDesafiosHandler getProcessarDesafios() {
		return new ProcessarDesafiosHandler(); // TODO
	}
}
