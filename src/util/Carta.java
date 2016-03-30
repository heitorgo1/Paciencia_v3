package util;

import java.awt.Color;


/**
 * Class representando uma carta de um baralho
 * @author Heitor e Ivan
 * */
public class Carta implements Comparable<Carta>{

	/**Valor da carta*/
	private int valor;
	/**Naipe da carta*/
	private Naipe naipe;

	/**Define se a carta está para cima ou para baixo*/
	private boolean paraCima;
	
	/**Valor do Rei*/
	public static final int K = 13;
	/**Valor da Rainha*/
	public static final int Q = 12;
	/**Valor do Valete*/
	public static final int J = 11;
	/**Valor do As*/
	public static final int AS = 1;
	
	/**Maior valor do jogo*/
	public static final int MAIOR_VALOR = K;
	/**Menor valor do jogo*/
	public static final int MENOR_VALOR = AS;
	
	public Carta (int valor, Naipe naipe) {
		this.valor = valor;
		this.naipe = naipe;
		paraCima = false;
	}
	
	public Color getColor() {
		return naipe.getColor();
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public Naipe getNaipe() {
		return naipe;
	}

	public void setNaipe(Naipe naipe) {
		this.naipe = naipe;
	}
	
	public boolean isParaCima() {
		return paraCima;
	}
	
	/**Inverte o valor da variável paraCima*/
	public void virarCarta() {
		paraCima = !paraCima;
	}
	
	public boolean isMaiorValor () {
		return valor==MAIOR_VALOR;
	}
	
	public boolean isMenorValor() {
		return valor==MENOR_VALOR;
	}
	
	/**Retorna uma representação de carta para baixo se paraCima for false,
	 * ou o valor da carta seguido do Naipe caso paraCima seja true.
	 * 
	 * @return String que representa a carta*/
	public String toString() {
		if (!isParaCima()) {
			return "[<>]";
		} 
		else {
			String str = "";
			if (valor == K) str += "K";
			else if (valor == Q) str += "Q";
			else if (valor == J) str += "J";
			else if (valor == AS) str += "AS";
			else str += valor;
			
			str += " "+naipe.name();
			return str;
		}
	}

	@Override
	public int compareTo(Carta o) {
		if (this.valor == o.getValor()) return 0;
		if (this.valor > o.getValor()) return 1;
		else return -1;
	}
	
}
