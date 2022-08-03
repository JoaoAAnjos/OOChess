package oochess.app.domain;

import java.time.LocalDateTime;

/**
 * This class represents a spontaneous match
 *
 */
public class PartidaEspontanea extends PartidaAbstract{

	/**
	 * The constructor for the class
	 * 
	 * @param corrente
	 * @param oponente
	 * @param resultado
	 * @param data
	 */
	public PartidaEspontanea(Jogador corrente, Jogador oponente, String resultado, LocalDateTime data) {
		super(corrente, oponente, resultado, data);
	}

}
