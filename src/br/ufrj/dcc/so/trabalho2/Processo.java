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
		
		System.out.println(String.format("Processo %2d terminou.", pid));
	}



	private void solicitaPagina() {
		
		int numPagina = random.nextInt(numPaginas);	
		
		gerenciadorDeMemoria.alocaPagina(pid, numPagina);
	}
	

}
