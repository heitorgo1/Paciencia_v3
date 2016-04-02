package model;

import java.util.ArrayList;
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
	public void inserirCarta(Carta carta) {
		cartas.push(carta);
	}
	
	/**Inserir cartas na pilha.*/
	public void inserirCartas (ArrayList<Carta> cartas) {
		this.cartas.addAll(cartas);
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
	
	/**Recebe uma coleção de cartas para serem colocadas ou não na pilha. 
	 * Depende da verificação de cada tipo de Pilha.
	 * @param cartas		Cartsa que querem ser inseridas
	 * @return Booleano representando se as cartas foram inseridas*/
	public boolean receberCartas(ArrayList<Carta> cartas) {
		
		if (cartas == null || cartas.size() <= 0 ) return false;
		
		for (int i = 0; i < cartas.size(); i++){
			if (verificarCarta(cartas.get(i))) {
				inserirCarta(cartas.get(i));
			}else {
				while (i-- > 0) puxarCartaTopo();
				return false;
			}
		}
		return true;
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
	
	private Carta getCartaParaCimaByValor(int valor) {
		for (Carta carta: cartas) {
			if (carta.getValor() == valor && carta.isParaCima()) return carta;
		}
		return null;
	}
	
	/**Puxa todas as cartas acima de uma carta de valor passado como parâmetro.
	 * @param valor		Valor da primeira carta do monte a ser movido
	 * @return Lista de cartas puxadas*/
	public ArrayList<Carta> puxarAPartirDeCarta(int valor){
		Carta primeira = getCartaParaCimaByValor(valor);
		ArrayList<Carta> result = new ArrayList<>();
		
		if (primeira == null) return result;
		
		Carta carta = puxarCartaTopo();
		while (carta != null && carta.compareTo(primeira) != 0 ) {
			result.add(0,carta);
			carta = puxarCartaTopo();
		}
		
		result.add(0,primeira);
		return result;
	}
	
	/**Checar se Pilha está vazia.
	 * @return Booleano representando se Pilha está vazia*/
	public boolean isEmpty() {
		return cartas.isEmpty();
	}
	
	public Carta[] getCartas() {
		Object[] obs = cartas.toArray();
		Carta[] cards = new Carta[obs.length];
		
		for (int i = 0; i < obs.length; i++) cards[i] = (Carta)obs[i];
			
		return cards;
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
