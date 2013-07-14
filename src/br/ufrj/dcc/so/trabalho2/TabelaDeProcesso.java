package br.ufrj.dcc.so.trabalho2;

import java.util.HashMap;

public class TabelaDeProcesso extends HashMap<Integer, Integer> {
	
	private static final long serialVersionUID = 1L;
	
	
	private int workingSetCount;
	
	public TabelaDeProcesso() {
		this.workingSetCount = 0;
	}

	
	@Override
	public Integer put(Integer numPagina, Integer frameLivre) {
		if (frameLivre >= 0) {
			workingSetCount++;
		}
		return super.put(numPagina, frameLivre);
	}
	
	
	@Override
	public Integer remove(Object numPagina) {
		Integer endereco = get(numPagina);
		
		if (endereco != null && endereco > 0) workingSetCount--;
		
		return super.remove(numPagina);
	}
	
	
	public int getWorkingSetCount() {
		return workingSetCount;
	}
	
	
}
