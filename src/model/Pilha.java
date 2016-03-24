package model;

import java.util.Stack;

import util.Carta;

/**Representa uma pilha do jogo Paciência. 
 * Pode receber cartas segundo as regras para cada tipo de Pilha.*/
public abstract class Pilha {
	
	/**Pilha de cartas*/
	protected Stack<Carta> cartas;
	
	public Pilha() {
		cartas = new Stack<>();
	}
	
	/**Cada Pilha implementa seu modo de verificar se pode receber uma carta.
	 * @return Booleano representando se a carta pode ser inserida ou não*/
	protected abstract boolean verificarCarta(Carta carta);
	
	/**Inserir carta na pilha.*/
	private void inserirCarta(Carta carta) {
		cartas.push(carta);
	}
	
	/**Recebe uma carta para ser colocada ou não na pilha. 
	 * Depende da verificação de cada tipo de Pilha.
	 * @param carta		Carta que quer ser inserida
	 * @return Booleano representando se a carta foi inserida*/
	public boolean receberCarta(Carta carta) {
		if (carta != null && verificarCarta(carta)) {
			inserirCarta(carta);
			return true;
		}
		return false;
	}
	
	
	/**Carta do topo da pilha.
	 * @return Carta do topo da pilha*/
	public Carta cartaTopo() {
		if (!isEmpty()) return cartas.peek();
		return null;
	}

	
	/**Retirar carta do topo da pilha.
	 * @return Carta do topo da pilha*/
	public Carta puxarCartaTopo() {
		if (!isEmpty()) return  cartas.pop();
		else return null;
	}
	
	/**Checar se Pilha está vazia.
	 * @return Booleano representando se Pilha está vazia*/
	public boolean isEmpty() {
		return cartas.isEmpty();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (Carta carta: cartas) {
			if (first) {
				sb.append(carta.toString());
				first = false;
			} else{ 
				sb.append(", ");
				sb.append(carta.toString());
			}
		}
		return sb.toString();
	}
}
