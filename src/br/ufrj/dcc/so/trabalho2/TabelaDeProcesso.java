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
	public Integer put(Pagina key, Integer endereco) {
		if (endereco >= 0) {
			workingSetCount++;
			listaLRU.addLast(key);
		}
		return super.put(key, endereco);
	}
	
	
	@Override
	public Integer remove(Object key) {
		Integer endereco = get(key);
		
		if (endereco != null && endereco >= 0) {
			workingSetCount--;
			listaLRU.remove(key);
		}
		
		return super.remove(key);
	}
	
	
	public int getWorkingSetCount() {
		return workingSetCount;
	}


	/***
	 * 
	 * @param pagina página que se tenta acessar
	 * @return <code>null</code> se a página não está na memória virtual, um número >= 0 se estiver na memória principal ou um número < 0 se estiver na área de swap.
	 */
	public Integer acessaPagina(Pagina pagina) {
		Integer endereco = get(pagina);
		if (endereco != null && endereco >= 0) {
			listaLRU.remove(pagina);
			listaLRU.addLast(pagina);
		}
		return endereco;
	}
	
	
	public Pagina getPaginaLRU() {
		return listaLRU.poll();
	}
	
	
}
