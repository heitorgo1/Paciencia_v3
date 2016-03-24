package model;

import java.util.ArrayList;

import util.Baralho;
import util.Carta;

public class Mesa {

	private ArrayList<Pilha> pilhas;
	
	public Mesa(Baralho baralho) {
		pilhas = new ArrayList<>();
		
		int qtd_estoque = 52-1-2-3-4-5-6-7;
		
		pilhas.add(new Estoque(baralho.puxarCartas(qtd_estoque)));
		pilhas.add(new Descarte());
		
		for (int i = 0; i < 4; i++) pilhas.add(new Fundacao());
		
		for (int i = 1; i <= 7; i++)  {
			Pilha fileira = new Fileira(baralho.puxarCartas(i));
			fileira.cartaTopo().virarCarta();
			pilhas.add(fileira);
		}
		
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		int index = 1;
		for (Pilha pilha: pilhas) {
			sb.append(index+" - ");
			sb.append(pilha.getClass().getName().toUpperCase().split("[.]")[1]);
			sb.append(" == ");
			sb.append(pilha);
			sb.append("\n");
			index++;
		}
		
		return sb.toString();
	}
}
