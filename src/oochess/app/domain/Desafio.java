package oochess.app.domain;

import java.time.LocalDateTime;

/**
 * This class represents a challenge
 * to a match
 *
 */
public class Desafio {
	
	private Jogador challenger;
	private Jogador challenged;
	private String codigo;
	private Torneio torneio;
	private LocalDateTime data;
	private DesafioStatus status;
	
	/**
	 * The constructor for the class
	 * 
	 * @param challenger
	 * @param challenged
	 * @param torneio - the tournament to which the match will belong, if any
	 * @param data
	 */
	public Desafio(Jogador challenger, Jogador challenged, Torneio torneio, LocalDateTime data) {
		this.challenger = challenger;
		this.challenged = challenged;
		this.torneio = torneio;
		this.data = data;
		this.status = DesafioStatus.PENDENTE;
		this.codigo = challenger.toString()+challenged.toString()+data.getDayOfYear()+"/"+data.getDayOfYear();
	}
	
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
	public String getCodigo() {
		return codigo;
	}

	public Jogador getChallenger() {
		return challenger;
	}

	public Jogador getChallenged() {
		return challenged;
	}
	
	/**
	 * Receives one of the player's of the challenge and returns the other
	 * 
	 * @param j - the player
	 * @return the other player in the challenge
	 */
	public Jogador getOtherJogador(Jogador j) {
		return challenger.equals(j) ? challenged : challenger;
	}

	public Torneio getTorneio() {
		return torneio;
	}

	public LocalDateTime getData() {
		return data;
	}

	public DesafioStatus getStatus() {
		return status;
	}
	
	public void setStatus(DesafioStatus newStatus) {
		status = newStatus;
	}
	
	/**
	 * Creates a new challenged match, based on the challenge's information 
	 * 
	 * @param jogador - the first player
	 * @param oponente - the second player
	 * @param resultado - the match result for the first player
	 * @ensures that a new challenged match is created
	 * @return a new challenged match
	 */
	public PartidaAbstract createPartida(Jogador jogador, Jogador oponente, String resultado) {
		return new PartidaDesafiada(jogador, oponente, resultado, data, codigo, torneio);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	/**
	 *Two challenges are equal if they have the same code
	 *
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Desafio other = (Desafio) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
