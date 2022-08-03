package oochess.app.domain;

import java.time.LocalDateTime;

/**
 * This class represents a tournament
 *
 */
public class Torneio {
	
	private String nome;
	private LocalDateTime dataInicio;
	private LocalDateTime dataFim;
	
	public Torneio(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public LocalDateTime getDataInicio() {
		return dataInicio;
	}
	
	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}

}
