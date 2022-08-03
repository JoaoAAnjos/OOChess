package oochess.app.domain;

import java.time.LocalDateTime;

/**
 * This class represents any type of match
 *
 */
public abstract class PartidaAbstract {
	
	protected Jogador corrente;
	protected Jogador oponente;
	protected LocalDateTime data;
	protected String resultado;
	
	/**
	 * The constructor for the class
	 * 
	 * @param corrente
	 * @param oponente
	 * @param resultado
	 * @param data
	 */
	protected PartidaAbstract(Jogador corrente, Jogador oponente, String resultado, LocalDateTime data) {
		this.corrente = corrente;
		this.oponente = oponente;
		this.resultado = resultado;
		this.data = data;
	}
	
	public Jogador getOponente() {
		return oponente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((corrente == null) ? 0 : corrente.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((oponente == null) ? 0 : oponente.hashCode());
		return result;
	}

	/**
	 *Two matches are equal if they have the same envolved players and the same date
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PartidaAbstract other = (PartidaAbstract) obj;
		if (corrente == null) {
			if (other.corrente != null)
				return false;
		} else if (!corrente.equals(other.corrente))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (oponente == null) {
			if (other.oponente != null)
				return false;
		} else if (!oponente.equals(other.oponente))
			return false;
		return true;
	}
}
