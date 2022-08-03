package oochess.app.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class represents a catalog of tournaments
 *
 */
public class CatTorneios {
	
	private List<Torneio> torneios = new ArrayList<>();
	
	public CatTorneios() {
		//Creation of tournaments so ClienteExemplo can run
		this.addTorneio(new Torneio("Torneio Xadrez da CADI"));
		this.addTorneio(new Torneio("Torneio Xadrez do Campo Grande"));
		this.addTorneio(new Torneio("Torneio Xadrez do Minicampus"));
		this.addTorneio(new Torneio("Torneio Xadrez do C5"));
	}
	
	public Optional<Torneio> getTorneio(String nomeTorneio) {
		for(Torneio t : torneios) {
			if(t.getNome().contentEquals(nomeTorneio)) {
				return Optional.of(t);
			}
		}
		return Optional.empty();
	}
	
	public void addTorneio(Torneio t) {
		torneios.add(t);
	}

}
