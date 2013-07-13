package br.ufrj.dcc.so.trabalho2;

public class GerenciadorDeMemoria {

	
	
	public void alocaPagina(int pid, int numPagina) {

		System.out.println(String.format("Processo %2d solicitando página %d.",
				pid, numPagina));
		
	}

}
