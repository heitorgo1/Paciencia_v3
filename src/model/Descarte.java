package model;

import util.Carta;

/**Representa uma Pilha de Descarte do jogo Paciência. 
 * É a pilha que possui todas as cartas que são puxadas
 * do Estoque.*/
public class Descarte extends Pilha {

	public Descarte (){
		super();
	}
	
	@Override
	protected boolean verificarCarta(Carta carta) {
		return false;
	}

}
