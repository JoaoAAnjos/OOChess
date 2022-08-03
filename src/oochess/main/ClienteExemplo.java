package oochess.main;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import oochess.app.OOChess;
import oochess.app.facade.Sessao;
import oochess.app.facade.dto.JogadorEElo;
import oochess.app.facade.exceptions.BibliotecaNaoSuportada;
import oochess.app.facade.exceptions.DesafioInvalido;
import oochess.app.facade.exceptions.TorneioInexistente;
import oochess.app.facade.exceptions.UtilizadorInexistente;
import oochess.app.facade.exceptions.VarianteEloInexistente;
import oochess.app.facade.handlers.DesafiarHandler;
import oochess.app.facade.handlers.ProcessarDesafiosHandler;
import oochess.app.facade.handlers.RegistarResultadoHandler;
import oochess.app.facade.handlers.RegistarUtilizadorHandler;

public class ClienteExemplo {
	
	private static String codigoDaPartida;
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		OOChess p = new OOChess();
		
		RegistarUtilizadorHandler regHandler = p.getRegistarUtilizadorHandler();
		
		
		try {
			regHandler.registarUtilizador("Felismina", "hortadafcul", "fel1sgamer");
			regHandler.registarUtilizador("Silvino", "bardoc2", "s1lv1n0");
			regHandler.registarUtilizador("Maribel", "torredotombo", "SkubaPatr0l");
		} catch (VarianteEloInexistente e) {
			e.printStackTrace();
		}
		
		
		
		// SSD - UC5
		Optional<Sessao> talvezSessao = p.autenticar("Silvino", "bardoc2");
		talvezSessao.ifPresent( (Sessao s) -> {
			
			DesafiarHandler desh = s.getDesafioParaPartidaHandler();
			
			try {
				desh.indicaTorneio("Torneio Xadrez da CADI");
				
				List<JogadorEElo> jogadoresEElos = desh.indicaDeltaElo(50);
				for(JogadorEElo j : jogadoresEElos) {
					System.out.println(j);
				}
				
				desh.indicaJogador("Maribel");
				codigoDaPartida = desh.indicaDetalhes(LocalDateTime.now().plusDays(1), "Amanhã vou finalmente derrotar-te!");
			} catch (TorneioInexistente | UtilizadorInexistente | BibliotecaNaoSuportada e) {
				e.printStackTrace();
			}
			
		});
		
		// SSD - UC6

		talvezSessao.ifPresent( (Sessao s) -> {
			RegistarResultadoHandler rh = s.getRegistarResultadoDePartida();
			rh.indicaPartidaEspontanea();
			try {
				rh.indicaDetalhes("Felismina", LocalDateTime.now());
				double novoEloDoSilvino = rh.indicarResultado("DERROTA");
				System.out.println("[NovoElo] Silvino: " + novoEloDoSilvino);
			} catch (UtilizadorInexistente | VarianteEloInexistente e) {
				e.printStackTrace();
			}
			
		});
		
		// SSD - UC7
		Optional<Sessao> talvezOutraSessao = p.autenticar("Maribel", "torredotombo");
		talvezOutraSessao.ifPresent( (Sessao s) -> {
			ProcessarDesafiosHandler pdh = s.getProcessarDesafios();
			boolean disponivel = true; 
			for (Object o : pdh.consultarDesafiosPendentes()) {
				pdh.respondeADesafio("o.getCodigo()???", disponivel);
				if (!disponivel) {
					pdh.indicaNovaData(LocalDateTime.now().plusDays(2));
				}				
				disponivel = !disponivel;
			}
		});
		
		// SSD - UC6 (alt)
		
		talvezOutraSessao.ifPresent( (Sessao s) -> {
			RegistarResultadoHandler rh = s.getRegistarResultadoDePartida();
			try {
				rh.indicaDesafio(codigoDaPartida);
				double novoEloDaMaribel = rh.indicarResultado("VITORIA"); // Poderia ser também EMPATE
				System.out.println("[NovoElo] Maribel: " + novoEloDaMaribel);
			} catch (DesafioInvalido | VarianteEloInexistente e) {
				e.printStackTrace();
			}
			
		});
	}
}
		