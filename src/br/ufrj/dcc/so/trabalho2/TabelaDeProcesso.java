package br.ufrj.dcc.so.trabalho2;

import java.util.HashMap;

public class TabelaDeProcesso extends HashMap<Integer, Integer> {
	
	private static final long serialVersionUID = 1L;
	
	
	private int workingSetCount;
	
	public TabelaDeProcesso() {
		this.workingSetCount = 0;
	}

	
	@Override
	public Integer put(Integer key, Integer value) {
		if (value >= 0) {
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
