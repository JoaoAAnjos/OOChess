package oochess.app.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import oochess.app.facade.dto.JogadorEElo;

/**
 * This class represents a catalog of users
 *
 */
public class CatJogadores {
	
	private List<Jogador> jogadores = new ArrayList<>();
	
	public Optional<Jogador> getJogador(String username) {
		for(Jogador j : jogadores) {
			if(j.getUsername().contentEquals(username)) {
				return Optional.of(j);
			}
		}
		return Optional.empty();
	}
	
	public void addJogador(Jogador j) {
		jogadores.add(j);
	}

	/**
	 * This method takes a player and a deltaElo and returns the list of users and their respective elos 
	 * in the catalog whose absolute difference between their elo and the player's elo is lower than delta
	 * 
	 * @param elo - the delta elo
	 * @param challenger - the player
	 * @requires challenger to exist
	 * @ensures that the difference between the user's elo and each player's elo in the list
	 * are lower than delta
	 * @return a list of users and their respective elos
	 */
	public List<JogadorEElo> newChallengeList(int elo, Jogador challenger) {
		return jogadores.stream().
				filter(j -> j.getElo()-challenger.getElo() < elo)
				.map(j -> new JogadorEElo(j.getUsername(), j.getElo()))
				.collect(Collectors.toList());
	}

}
