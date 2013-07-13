package br.ufrj.dcc.so.trabalho2;

import java.util.HashMap;
import java.util.LinkedList;

public class GerenciadorDeMemoria {

	
	private HashMap<Integer, HashMap<Integer, Integer>> tabelasDePaginas;
	private LinkedList<Integer> framesLivres;
	
	
	public GerenciadorDeMemoria(int capacidade) {
		this.tabelasDePaginas= new HashMap<Integer, HashMap<Integer,Integer>>();
		
		this.framesLivres = new LinkedList<Integer>();
		for (int i = 0; i < capacidade; i++) {
			framesLivres.add(i);
		}
	}
	
	public void alocaPagina(int pid, int numPagina) {
		
		System.out.println(String.format("Processo %2d solicitou a página %d.",
				pid, numPagina));
		
		HashMap<Integer, Integer> tabelaDoProcesso = tabelasDePaginas.get(pid);
		
		if (tabelaDoProcesso == null) {
			tabelaDoProcesso = new HashMap<Integer, Integer>();
			tabelasDePaginas.put(pid, tabelaDoProcesso);
		}
		
		Integer pagina = tabelaDoProcesso.get(numPagina);
		
		if (pagina == null) {
			Integer frameLivre = framesLivres.poll();
			if (frameLivre != null) {
				tabelaDoProcesso.put(numPagina, frameLivre);
			}
			else {
				System.out.println("Memória cheia");
			}				
		}
		
		System.out.println(tabelasDePaginas);
		
	}

}
