package oochess.app.domain;

import java.time.LocalDateTime;

/**
 * This class represents a match that comes from a challenge
 *
 */
public class PartidaDesafiada extends PartidaAbstract{
	
	private String desafio;
	private Torneio torneio;
	
	/**
	 * The constructor for the class
	 * 
	 * @param corrente
	 * @param oponente
	 * @param resultado
	 * @param data
	 * @param codigo - the challenge's code
	 * @param torneio - the tournament to which the match belongs, if any
	 */
	public PartidaDesafiada(Jogador corrente, Jogador oponente, String resultado, LocalDateTime data, String codigo, Torneio torneio) {
		super(corrente, oponente, resultado, data);
		this.desafio = codigo;
		this.torneio = torneio;
	}

	public String getDesafio() {
		return desafio;
	}

	public Torneio getTorneio() {
		return torneio;
	}
	

}
