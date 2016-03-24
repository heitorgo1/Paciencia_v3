package util;

import java.awt.Color;

/**
* Enumeração dos Naipes possíveis em um baralho
* @author Heitor e Ivan
*/
public enum Naipe {

	OURO,
	PAUS,
	ESPADA,
	COPAS;
	
	/**A cor de uma Carta depende do seu Naipe. Caso seja OURO ou COPAS, será vermelha
	 * , caso seja PAUS ou ESPADA será preta.
	 * @return	Cor da carta a partir do Naipe*/
	public Color getColor() {
		boolean isRed = this == OURO || this == COPAS;
		boolean isBlack = this == ESPADA || this == PAUS;
		
		return isRed?Color.RED:isBlack?Color.BLACK:Color.WHITE;
	}
}
