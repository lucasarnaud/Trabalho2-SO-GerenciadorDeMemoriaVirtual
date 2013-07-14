package br.ufrj.dcc.so.trabalho2;

import java.util.Random;

public class Processo extends Thread {
	
	
	private static final int DOIS_SEGUNDOS = 2000;
	
	private final int pid;
	private final int numPaginas;
	private final GerenciadorDeMemoria gerenciadorDeMemoria;
	
	private final Random random;
	
	
	public Processo(int pid, int numPaginas, GerenciadorDeMemoria gerenciadorDeMemoria) {
		this.pid = pid;
		this.numPaginas = numPaginas;
		this.gerenciadorDeMemoria = gerenciadorDeMemoria;
		this.random = new Random();
	}



	@Override
	public void run() {
		
		while (true) {
			
			solicitaPagina();
			
			try {
				Thread.sleep(DOIS_SEGUNDOS);
			} catch (InterruptedException e) {
				break;
			}
			
		}
		
		System.out.println(String.format("Processo %s terminou.", toString()));
	}



	private void solicitaPagina() {
		
		int numPagina = random.nextInt(numPaginas);	
		
		Pagina pagina = new Pagina(this, numPagina);
		
		gerenciadorDeMemoria.alocaPagina(pagina);
	}
	
	
	@Override
	public String toString() {
		return "P" + pid;
	}
	

	public int getPid() {
		return pid;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pid;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Processo other = (Processo) obj;
		if (pid != other.pid)
			return false;
		return true;
	}
	
}
