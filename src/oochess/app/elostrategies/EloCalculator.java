package oochess.app.elostrategies;

/**
 * This interface sets the methods that a elo strategy
 * developed for this application must implement
 *
 */
public interface EloCalculator {
	
	/**
	 * @return the elo strategy default Elo for new users
	 */
	public double defaultElo();
	
	/**
	 * Calculates the newElo for two players, based on the first player's
	 * match result
	 * 
	 * @param jogador - the first player
	 * @param oponente - the second player
	 * @param resultado - the first player's match result
	 * @requires the first and second player to exist
	 * and result to be one of the following "VITORIA","DERROTA","EMPATE"
	 * @return a double array with the first and second player new elos, respectively
	 */
	public double[] calculateElo(double jogador, double oponente, String resultado);
}
