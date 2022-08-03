package oochess.app.facade.dto;

/**
 * This class applies the DTO pattern on 
 * the Jogador class, allowing only acess to a
 * players's username and elo
 *
 */
public class JogadorEElo {
	
	private String username;
	private double elo;
	
	public JogadorEElo(String username, double elo) {
		this.username = username;
		this.elo = elo;
	}
	
	public String getUsername() {
		return username;
	}
	
	public double getElo() {
		return elo;
	}
	
	public String toString() {
		return (username + " : " + elo);
	}

}
