package br.ufrj.dcc.so.trabalho2;

import java.util.HashMap;
import java.util.LinkedList;

public class GerenciadorDeMemoria {
	private HashMap<Processo, TabelaDeProcesso> tabelasDePaginas;
	private LinkedList<Integer> framesLivres;
	private final int workingSetLimit;
	private int proximoEnderecoAreaDeSwap;
	
	
	public GerenciadorDeMemoria(int capacidade, int workingSetLimit) {
		this.tabelasDePaginas = new HashMap<Processo, TabelaDeProcesso>();
		this.workingSetLimit = workingSetLimit;
		
		this.framesLivres = new LinkedList<Integer>();
		for (int i = 0; i < capacidade; i++) {
			framesLivres.add(i);
		}
		
		this.proximoEnderecoAreaDeSwap = -1;
	}
	
	public synchronized void alocaPagina(Pagina pagina) {
		Processo processo = pagina.getProcesso();
		
		System.out.println();
		System.out.println(String.format("Processo %s solicitou a página %d.",
				processo.toString(), pagina.getNumPagina()));
		
		TabelaDeProcesso tabelaDoProcesso = tabelasDePaginas.get(processo);
		
		if (tabelaDoProcesso == null) {
			tabelaDoProcesso = new TabelaDeProcesso();
			tabelasDePaginas.put(processo, tabelaDoProcesso);
		}
		
		Integer enderecoPagina = tabelaDoProcesso.acessaPagina(pagina);
		if (enderecoPagina == null) { //pagina nao está na memoria virtual
			System.out.println("------- Page fault! --------");
			realizaAlocacao(pagina, processo, tabelaDoProcesso, proximoEnderecoAreaDeSwap--);
		}
		else if (enderecoPagina < 0) {
			realizaAlocacao(pagina, processo, tabelaDoProcesso, enderecoPagina);
		}
		
		System.out.println(tabelasDePaginas);
		System.out.println(framesLivres);
		System.out.println();

	}

	private void realizaAlocacao(Pagina pagina, Processo processo,
			TabelaDeProcesso tabelaDoProcesso, Integer enderecoAlocacao) {
		if (tabelaDoProcesso.getWorkingSetCount() < workingSetLimit) {
			Integer frameLivre = framesLivres.poll();
			if (frameLivre != null) {
				tabelaDoProcesso.put(pagina, frameLivre);				
			}
			else {
				System.out.println("Memória cheia");
				//TODO realizar swap out de pagina menos usada no geral
			}				
		}
		else {
			System.out.println(String.format(
					"Processo %s atingiu working set limit.", processo.toString()));
			Pagina paginaLRU = tabelaDoProcesso.getPaginaLRU();
			
			//swap-out
			Integer enderecoLRU = tabelaDoProcesso.remove(paginaLRU);
			tabelaDoProcesso.put(paginaLRU, enderecoAlocacao);
			
			tabelaDoProcesso.put(pagina, enderecoLRU);
		}
	}

}
