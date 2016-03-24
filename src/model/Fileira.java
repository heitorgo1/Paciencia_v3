package model;

import java.util.ArrayList;

import util.Carta;

/**Representa uma Pilha Fileira do jogo Paciência. 
 * Esta pilha possui cartas viradas para baixo e para cima. 
 * Suas condições para receber novas cartas são:
 * 	- Pilha vazia e carta recebida Rei;
 *  - Carta do topo virada para cima, e carta recebida 1 valor abaixo do topo e de cor diferente.*/
public class Fileira extends Pilha{
	
	public Fileira() {
		super();
	}
	
	public Fileira(ArrayList<Carta> cartasParaBaixo) {
		this();
		cartas.addAll(cartasParaBaixo);
	}

	@Override
	protected boolean verificarCarta(Carta carta) {
		if (!carta.isParaCima()) return false;
		
		if (isEmpty()) {
			if (carta.getValor() == Carta.MAIOR_VALOR) return true;
			return false;
		}
		
		Carta topo = cartaTopo();
		
		if (!topo.isParaCima()) return false;
		
		if (topo.getValor()-1 == carta.getValor() && topo.getColor() != carta.getColor())
			return true;
	
		
		return false;
	}

}
