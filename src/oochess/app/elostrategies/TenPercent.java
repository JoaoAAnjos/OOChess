package oochess.app.elostrategies;

/**
 * Implementation of the TenPercent strategy
 *
 */
public class TenPercent implements EloCalculator{

	@Override
	public double defaultElo() {
		return 50;
	}

	@Override
	public double[] calculateElo(double jogador, double oponente, String resultado) {
		double[] result = new double[2];
		double eloChange = Math.abs(jogador-oponente)*0.1 + 5;
		if(resultado.contentEquals("VITORIA")) {
			result[0] = jogador + eloChange;
			result[1] = oponente - eloChange;
		}else if(resultado.contentEquals("DERROTA")) {
			result[0] = jogador - eloChange;
			result[1] = oponente + eloChange;
		}else if(resultado.contentEquals("EMPATE")) {
			eloChange = (jogador-oponente)*0.05;
			result[0] = jogador > oponente ? jogador-eloChange : jogador+eloChange;
			result[1] = jogador > oponente ? oponente+eloChange : oponente-eloChange;
		}
		return result;
	}
	
}
