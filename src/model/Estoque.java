package model;

import java.util.ArrayList;

import util.Carta;

/**Representa uma Pilha de Estoque do jogo Paciência. 
 * É a pilha que possui todas as suas cartas viradas para baixo.
 * Não recebe cartas de outras Pilhas, e apenas move cartas
 * diretamente para o Descarte.*/
public class Estoque extends Pilha {

	public Estoque() {
		super();
	}
	
	public Estoque(ArrayList<Carta> cartasParaBaixo) {
		this();
		cartas.addAll(cartasParaBaixo);
	}
	
	@Override
	protected boolean verificarCarta(Carta carta) {
		return false;
	}

}
