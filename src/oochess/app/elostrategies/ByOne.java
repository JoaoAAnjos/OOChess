package oochess.app.elostrategies;

/**
 * Implementation of the ByOne elo strategy
 *
 */
public class ByOne implements EloCalculator{

	@Override
	public double defaultElo() {
		return 5;
	}

	@Override
	public double[] calculateElo(double jogador, double oponente, String resultado) {
		double[] result = new double[2];
		if(resultado.contentEquals("VITORIA")) {
			result[0] = jogador+1;
			result[1] = jogador-1;
		}else if(resultado.contentEquals("DERROTA")) {
			result[0] = jogador-1;
			result[1] = oponente+1;
		}else if(resultado.contentEquals("EMPATE")) {
			result[0] = jogador;
			result[1] = oponente;
		}
		return result;
	}

}
