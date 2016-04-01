package cmdUi;

import java.util.ArrayList;

import model.Pilha;
import util.Carta;

public class PilhaListView {

	private ArrayList<Pilha> pilhas;
	
	public PilhaListView (ArrayList<Pilha> pilhas) {
		this.pilhas = pilhas;
	}

	public void drawPilhas() {
		
		int index = 1;
		for (Pilha pilha: pilhas) {
			Main.print((index++)+" - ");
			Main.print(pilha.getClass().getName().toUpperCase().split("[.]")[1]);
			Main.print(" == "+drawCards(pilha));
			Main.print("\n");
		}
		Main.print("\n");
	}
	
	public String drawCards(Pilha pilha) {
		StringBuilder sb = new StringBuilder();
		Carta[] cartas = pilha.getCartas();
		
		String paraBaixo = "[<>]";
		String valor;
		
		boolean first = true;
		for (Carta carta: cartas) {
			switch (carta.getValor()) {
			case Carta.K: valor ="K "; break;
			case Carta.Q: valor ="Q "; break;
			case Carta.J: valor = "J "; break;
			case Carta.AS: valor = "AS "; break;
			default: valor = carta.getValor()+" "; break;
			}
			
			valor += carta.getNaipe().name();
			
			if (first) {
				first = false;
			} else sb.append(", ");
			
			if (carta.isParaCima()) {
				sb.append(valor);
			} else sb.append(paraBaixo);
			
			
		}
		return sb.toString();
	}
}
