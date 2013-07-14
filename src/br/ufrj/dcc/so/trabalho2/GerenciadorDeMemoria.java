package br.ufrj.dcc.so.trabalho2;

import java.util.HashMap;
import java.util.LinkedList;

public class GerenciadorDeMemoria {

	
	private HashMap<Processo, TabelaDeProcesso> tabelasDePaginas;
	private LinkedList<Integer> framesLivres;
	private final int workingSetLimit;
	
	
	public GerenciadorDeMemoria(int capacidade, int workingSetLimit) {
		this.tabelasDePaginas = new HashMap<Processo, TabelaDeProcesso>();
		
		this.framesLivres = new LinkedList<Integer>();
		for (int i = 0; i < capacidade; i++) {
			framesLivres.add(i);
		}
		
		this.workingSetLimit = workingSetLimit;
	}
	
	public synchronized void alocaPagina(Pagina pagina) {
		Processo processo = pagina.getProcesso();
		
		System.out.println();
		System.out.println(String.format("Processo %2d solicitou a página %d.",
				processo.getPid(), pagina.getNumPagina()));
		
		TabelaDeProcesso tabelaDoProcesso = tabelasDePaginas.get(processo);
		
		if (tabelaDoProcesso == null) {
			tabelaDoProcesso = new TabelaDeProcesso();
			tabelasDePaginas.put(processo, tabelaDoProcesso);
		}
		
		if (!tabelaDoProcesso.containsKey(pagina)) {
			System.out.println("------- Page fault! --------");
			if (tabelaDoProcesso.getWorkingSetCount() < workingSetLimit) {
				Integer frameLivre = framesLivres.poll();
				if (frameLivre != null) {
					tabelaDoProcesso.put(pagina, frameLivre);				
				}
				else {
					System.out.println("Memória cheia");
				}				
			}
			else {
				System.out.println(String.format(
						"Processo %2d atingiu working set limit.", processo.getPid()));
			}
		}
		
		System.out.println(tabelasDePaginas);
		System.out.println(framesLivres);
		System.out.println();

	}

}
