package util;

import java.util.ArrayList;
import java.util.Collections;


/**Representa um baralho de 52 cartas. Cartas possuem valores entre AS e K,
 *  e naipes OURO, PAUS, ESPADA e COPAS.*/
public class Baralho {

	/**Lista de cartas*/
	private ArrayList<Carta> cartas;
	
	/**Constrói um baralho de 52 cartas.*/
	public Baralho () {
		cartas = new ArrayList<>();
		
		for (Naipe naipe: Naipe.values()) {
			for (int val = Carta.AS; val <= Carta.K; val++) {
				cartas.add(new Carta(val,naipe));
			}
		}
	}
	
	/**Instanciação com cartas embaralhadas.*/
	public Baralho (boolean embaralhar) {
		this();
		if (embaralhar) {
			Collections.shuffle(cartas);
		}
	}
	
	
	/**Puxar cartas do topo do baralho.
	 * @param qtd	Quantidade de cartas puxadas
	 * @return Lista de cartas puxadas*/
	public ArrayList<Carta> puxarCartas(int qtd) {
		ArrayList<Carta> cartasPuxadas = new ArrayList<>();
		
		while (!isEmpty() && qtd > 0) {
			Carta cartaPuxada = cartas.remove(cartas.size()-1);
			cartasPuxadas.add(cartaPuxada);
			qtd--;
		}
		
		return cartasPuxadas;
	}
	
	/**Puxar todas as cartas do baralho.
	 * @return Lista de cartas puxadas*/
	public ArrayList<Carta> puxarTodasAsCartas() {
		ArrayList<Carta> cartasPuxadas = new ArrayList<>();
		
		cartasPuxadas.addAll(cartas);
		Collections.reverse(cartasPuxadas);
		cartas.clear();
		
		return cartasPuxadas;
	}
	
	/**Checar se o baralho está vazio.
	 * @return Booleano sobre estado do baralho*/
	public boolean isEmpty() {
		return cartas.isEmpty();
	}
}
