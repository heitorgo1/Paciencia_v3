package model;


import util.Carta;

/**Representa uma Pilha de Fundação do jogo Paciência. 
 * É a pilha onde ficam os montes de cartas para cada naipe.
 * Se o jogador preencher de forma crescente cada Fundação dos 4 naipes,
 * ele vence o jogo.*/
public class Fundacao extends Pilha {

	public Fundacao() {
		super();
	}
	
	@Override
	protected boolean verificarCarta(Carta carta) {
		if (!carta.isParaCima()) return false;
		
		if (isEmpty()) {
			if (carta.getValor() == Carta.MENOR_VALOR) return true;
			return false;
		}
		
		Carta topo = cartaTopo();
		
		if (topo.getValor()+1 == carta.getValor() && topo.getNaipe() == carta.getNaipe())
			return true;
		
		return false;
	}

}
