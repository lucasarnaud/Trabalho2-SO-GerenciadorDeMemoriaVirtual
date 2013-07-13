package br.ufrj.dcc.so.trabalho2;

import java.util.Scanner;

public class Main {


	private static final int CINCO_SEGUNDOS = 5000;
	private static final String TERMINAR = "s";

	
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Informe número de processos: ");
		int numProcessos = scanner.nextInt();

		System.out.print("Informe número de páginas dos processos: ");
		int numPaginas = scanner.nextInt();

		System.out.print("Informe capacidade da memória principal: ");
		int capacidade = scanner.nextInt();
		System.out.print("Informe o working set limit: ");
		int workingSetLimit = scanner.nextInt();
		GerenciadorDeMemoria gerenciadorDeMemoria = new GerenciadorDeMemoria(
				capacidade, workingSetLimit);
		
		Processo[] processos = criarProcessos(numPaginas, gerenciadorDeMemoria,
				numProcessos);
		
		while (!scanner.nextLine().equals(TERMINAR));
		
		terminarProcessos(processos);

	}



	private static Processo[] criarProcessos(int numPaginas,
			GerenciadorDeMemoria gerenciadorDeMemoria, int numProcessos) {
		Processo[] processos = new Processo[numProcessos];

		for (int i = 0; i < processos.length; i++) {
			Processo processo = new Processo(i, numPaginas, gerenciadorDeMemoria);
			processo.start();

			processos[i] = processo;

			try {
				Thread.sleep(CINCO_SEGUNDOS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		return processos;
	}



	private static void terminarProcessos(Processo[] processos) {
		
		for (int i = 0; i < processos.length; i++) {
			processos[i].interrupt();
		}
		
	}

}
