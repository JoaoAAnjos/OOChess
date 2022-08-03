package oochess.app.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class represents a player (system user)
 *
 */
public class Jogador {
	
	private String username;
	private String discordUsername;
	private String password;
	private double elo;
	private List<Desafio> desafios = new ArrayList<>();
	private List<PartidaAbstract> partidas = new ArrayList<>();
	
	/**
	 * The class constructor
	 * 
	 * @param username
	 * @param discordUsername
	 * @param password
	 * @param elo
	 */
	public Jogador(String username, String discordUsername, String password, double elo) {
		this.username = username;
		this.discordUsername = discordUsername;
		this.password = password;
		this.elo = elo;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getDiscordUsername() {
		return discordUsername;
	}
	
	public Double getElo() {
		return elo;
	}
	
	public Optional<Desafio> getDesafio(String codigo) {
		for(Desafio d : desafios) {
			if(d.getCodigo().contentEquals(codigo)) {
				return Optional.of(d);
			}
		}
		return Optional.empty();
	}
	
	public boolean hasPassword(String pass) {
		return password.contentEquals(pass);
	}
	
	public void changeElo(Double newElo) {
		elo = newElo;
	}
	
	public String addDesafio(Jogador challenger, Jogador challenged, Torneio torneio, LocalDateTime data) {
		Desafio d = new Desafio(challenger, challenged, torneio, data);
		desafios.add(d);
		return d.getCodigo();
	}
	
	public void addPartida(PartidaAbstract p) {
		partidas.add(p);
	}
	
	/**
	 * Creates a new spontaneous match
	 * 
	 * @param corrente
	 * @param oponente
	 * @param resultado
	 * @param data
	 * @return the newly created spontaneous match
	 */
	public PartidaAbstract newPartida(Jogador corrente, Jogador oponente, String resultado, LocalDateTime data) {
		return new PartidaEspontanea(corrente, oponente, resultado, data);
	}
	
	/**
	 * Gets the player's five last opponent's username
	 * 
	 * @return A list with the opponent's username
	 */
	public List<String> ultimos5Oponentes() {
		return partidas.subList(Math.max(partidas.size()-5, 0), partidas.size()).stream()
				.map(p -> p.getOponente().getUsername()).collect(Collectors.toList());
	}
	
	public String toString() {
		return username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(elo);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	/**
	 *Two players are equal if they have the same username and password
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jogador other = (Jogador) obj;
		if (Double.doubleToLongBits(elo) != Double.doubleToLongBits(other.elo))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	
}
