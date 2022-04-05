package view;

import java.util.concurrent.Semaphore;

import controller.ThreadAeroporto;

public class Main {

	public static void main(String[] args) {
		
		int permissoes = 2;
		int numAvioes = 10;
		
		Semaphore semaforoPista = new Semaphore (permissoes);
		Semaphore pistaSul = new Semaphore(1);
		Semaphore pistaNorte = new Semaphore(1);
		
		for(int idAviao=0; idAviao<numAvioes ; idAviao++) {
			
			Thread tAeroporto = new ThreadAeroporto(idAviao, semaforoPista, pistaNorte, pistaSul);
			tAeroporto.start();
			
		}

	}

}
