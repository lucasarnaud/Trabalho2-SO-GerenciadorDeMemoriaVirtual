package br.ufrj.dcc.so.trabalho2;

import java.util.HashMap;
import java.util.LinkedList;

public class TabelaDeProcesso extends HashMap<Pagina, Integer> {
	
	private static final long serialVersionUID = 1L;
	
	
	private LinkedList<Pagina> listaLRU;
	private int workingSetCount;
	
	public TabelaDeProcesso() {
		this.workingSetCount = 0;
		this.listaLRU = new LinkedList<Pagina>();
	}

	
	@Override
	public Integer put(Pagina key, Integer value) {
		if (key.getNumPagina() >= 0) {
			workingSetCount++;
		}
		return super.put(key, value);
	}
	
	
	@Override
	public Integer remove(Object key) {
		Integer endereco = get(key);
		
		if (endereco != null && endereco > 0) workingSetCount--;
		
		return super.remove(key);
	}
	
	
	public int getWorkingSetCount() {
		return workingSetCount;
	}
	
	
}
