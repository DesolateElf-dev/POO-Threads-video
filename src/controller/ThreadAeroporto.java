package controller;

import java.util.concurrent.Semaphore;

public class ThreadAeroporto extends Thread{
	
	private int fatorTempo = 500;
	private int idAviao;
	private Semaphore semaforoPista;
	private Semaphore pistaSul;
	private Semaphore pistaNorte;
	
	public ThreadAeroporto(int idAviao, Semaphore semaforoPista, Semaphore pistaNorte, Semaphore pistaSul) {
		this.idAviao = idAviao;
		this.semaforoPista = semaforoPista;
		this.pistaNorte = pistaNorte;
		this.pistaSul = pistaSul;
	}
	
	@Override
	public void run() {
		
		try {
			semaforoPista.acquire();
			aviaoManobrando();
			aviaoTaxiando();
			
			int pista = (int)((Math.random()*2)+1); //1 - Pista Norte | 2 - Pista Sul
			
			if (pista == 1) {
				pistaNorte.acquire();
				aviaoDecolando(pista);
				System.out.printf("\nO avião %d se afastou da area", idAviao);
				semaforoPista.release();
				pistaNorte.release();				
				
			} else {
				pistaSul.acquire();
				aviaoDecolando(pista);
				System.out.printf("\nO avião %d se afastou da area", idAviao);
				semaforoPista.release();
				pistaSul.release();				
			}
			aviaoAfastando();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	private void aviaoManobrando(){
		
		System.out.printf("\nO aviao %d esta manobrando", idAviao);
		
		int tempo = aleatorioEntre(3,7);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
	
	private void aviaoTaxiando(){
		
		System.out.printf("\nO aviao %d esta taxiando", idAviao);
		
		int tempo = aleatorioEntre(5,10);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	private void aviaoDecolando(int pista) {
		
		if(pista==1) {
			System.out.printf("\nO aviao %d esta decolando pela pista Norte", idAviao);
		} else {
			System.out.printf("\nO aviao %d esta decolando pela pista Sul", idAviao);
		}
		
		int tempo = aleatorioEntre(1,4);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	private void aviaoAfastando() {
				
		int tempo = aleatorioEntre(3,8);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private int aleatorioEntre(int inicio, int fim) {
		
		fim = fim-inicio;
		fim++;

		int tempo = (int)((Math.random()*fim)+inicio)*fatorTempo;
		
		return tempo;
	}

}
