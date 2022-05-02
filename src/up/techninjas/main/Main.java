package up.techninjas.main;

import up.techninjas.jogo.Dama;
//import up.techninjas.pecas.Pecas;
import up.techninjas.tabuleiro.Tabuleiro;

public class Main {

	static Tabuleiro tabuleiro = new Tabuleiro();
	static Dama jogo = new Dama();
	static boolean continuar;
	
	public static void main(String[] args) {
		
		do {
			tabuleiro.criarPecas();
			/*	TESTE FOR EACH COM ARRAY DE OUTRA CLASSE
			for(Pecas pecas: tabuleiro.peca) {
				System.out.println(pecas.getIndex() + " " + pecas.getPeca());
			}
			*/
			Tabuleiro.printIndex();
			tabuleiro.criarTab();
			jogo.jogar();
			continuar = jogo.continuar();
			
		}while(continuar);
			
	}

}
