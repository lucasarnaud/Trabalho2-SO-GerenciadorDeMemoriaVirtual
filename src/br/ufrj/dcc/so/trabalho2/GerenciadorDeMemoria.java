package br.ufrj.dcc.so.trabalho2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.SortedMap;

public class GerenciadorDeMemoria {
	private HashMap<Integer, TabelaDeProcesso> tabelasDePaginas;
	private LinkedList<Integer> framesLivres;
	private final int workingSetLimit;
	
	
	public GerenciadorDeMemoria(int capacidade, int workingSetLimit) {
		this.tabelasDePaginas = new HashMap<Integer, TabelaDeProcesso>();
		
		this.framesLivres = new LinkedList<Integer>();
		for (int i = 0; i < capacidade; i++) {
			framesLivres.add(i);
		}
		
		this.workingSetLimit = workingSetLimit;
	}
	
	public synchronized void alocaPagina(int pid, int numPagina) {
		
		System.out.println(String.format("Processo %2d solicitou a página %d.",
				pid, numPagina));
		
		TabelaDeProcesso tabelaDoProcesso = tabelasDePaginas.get(pid);
		
		if (tabelaDoProcesso == null) {
			tabelaDoProcesso = new TabelaDeProcesso();
			tabelasDePaginas.put(pid, tabelaDoProcesso);
		}
		
		if (!tabelaDoProcesso.containsKey(numPagina)) {
			if (tabelaDoProcesso.getWorkingSetCount() < workingSetLimit) {
				Integer frameLivre = framesLivres.poll();
				if (frameLivre != null) {
					tabelaDoProcesso.put(numPagina, frameLivre);				
				}
				else {
					System.out.println("Memória cheia");
				}				
			}
			else {
				System.out.println(String.format(
						"Processo %2d atingiu working set limit.", pid));
			}
		}
		
		System.out.println(tabelasDePaginas);
		System.out.println(framesLivres);

	}

}
