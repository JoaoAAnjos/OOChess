package oochess.app;

import java.util.Optional;

import oochess.app.domain.CatJogadores;
import oochess.app.domain.CatTorneios;
import oochess.app.facade.Sessao;
import oochess.app.facade.handlers.RegistarUtilizadorHandler;

/**
 * Esta é a classe do sistema.
 */
public class OOChess {
	
	private CatJogadores jogadores = new CatJogadores();
	private CatTorneios torneios = new CatTorneios();

	public RegistarUtilizadorHandler getRegistarUtilizadorHandler() {
		return new RegistarUtilizadorHandler(this.jogadores);
	}
	
	/**
	 * Returns an optional Session representing the authenticated user.
	 * @param username
	 * @param password
	 * @return a Optional that has a session if the user exists, or empty otherwise
	 */
	public Optional<Sessao> autenticar(String username, String password) {
		return jogadores.getJogador(username).filter(j -> j.hasPassword(password)).map(j -> new Sessao(j, jogadores, torneios));
	}
	
}
