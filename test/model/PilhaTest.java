package model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import util.Carta;
import util.Naipe;

public class PilhaTest {

	
	public ArrayList<Carta> instanciarCartasParaFileira() {
		Carta carta1 = new Carta(Carta.K, Naipe.OURO);
		carta1.virarCarta();
		Carta carta2 = new Carta(Carta.Q, Naipe.PAUS);
		carta2.virarCarta();
		Carta carta3 = new Carta(Carta.J, Naipe.COPAS);
		carta3.virarCarta();
		Carta carta4 = new Carta(10, Naipe.ESPADA);
		carta4.virarCarta();
		ArrayList<Carta> cartas = new ArrayList<>();
		cartas.add(carta1); cartas.add(carta2);
		cartas.add(carta3); cartas.add(carta4);
		return cartas;
	}
	
	public ArrayList<Carta> instanciarCartasParaFundacao() {
		Carta carta1 = new Carta(Carta.AS, Naipe.OURO);
		carta1.virarCarta();
		Carta carta2 = new Carta(2, Naipe.OURO);
		carta2.virarCarta();
		Carta carta3 = new Carta(3, Naipe.OURO);
		carta3.virarCarta();
		Carta carta4 = new Carta(4, Naipe.OURO);
		carta4.virarCarta();
		ArrayList<Carta> cartas = new ArrayList<>();
		cartas.add(carta1); cartas.add(carta2);
		cartas.add(carta3); cartas.add(carta4);
		return cartas;
	}
	
	@Test
	public void pilhaVaziaTest() {
		Pilha estoque = new Estoque();
		
		assertTrue("Pilha deveria estar vazia", estoque.isEmpty());
	}
	
	@Test
	public void receberCartaFileiraTrueTest() {
		Pilha fileira = new Fileira();
		ArrayList<Carta> cartas = instanciarCartasParaFileira();
		
		for (Carta carta: cartas) {
			assertTrue("Pilha ("+fileira+") deveria aceitar a carta "+carta, fileira.receberCarta(carta));
		}
	}
	
	@Test
	public void receberCartaFileiraFalseTest() {
		Pilha fileira = new Fileira();
		ArrayList<Carta> cartas = instanciarCartasParaFileira();
		Carta cartaTest;
		
		for (int val = Carta.AS; val <= Carta.Q; val++) {
			cartaTest = new Carta(val, Naipe.OURO);
			assertFalse("Fileira vazia ("+fileira+") não deveria aceitar carta diferente de K", fileira.receberCarta(cartaTest));
		}
		
		for (Carta carta: cartas) {
			fileira.receberCarta(carta);
		}
		
		//Carta do topo da fileira no momento: 10 ESPADA
		
		for (Naipe naipe: Naipe.values()) {
			for (int val = Carta.AS; val <= Carta.K; val++) {
				
				if (val == 9 && (naipe == Naipe.OURO || naipe == Naipe.COPAS)) continue;
				
				cartaTest = new Carta(val, naipe);
				assertFalse("Fileira ("+fileira+") não deveria aceitar a carta "+cartaTest, fileira.receberCarta(cartaTest));
			}
		}
		
		assertFalse("Fileira ("+fileira+") não deveria aceitar null ", fileira.receberCarta(null));
	}
	
	@Test
	public void receberCartaFundacaoTrueTest() {
		Pilha fundacao = new Fundacao();
		ArrayList<Carta> cartas = instanciarCartasParaFundacao();
		
		for (Carta carta: cartas) {
			assertTrue("Fundacao ("+fundacao+") deveria aceitar a carta "+carta, fundacao.receberCarta(carta));
		}
	}
	
	@Test
	public void receberCartaFundacaoFalseTest() {
		Pilha fundacao = new Fundacao();
		ArrayList<Carta> cartas = instanciarCartasParaFundacao();
		
		
		for (int val = 2; val <= Carta.K; val++) {
			Carta cartaTest = new Carta(val, Naipe.OURO);
			assertFalse("Fundacao ("+fundacao+") não deveria aceitar carta diferente de AS", fundacao.receberCarta(cartaTest));
		}
		
		for (Carta carta: cartas) {
			fundacao.receberCarta(carta);
		}
		
		//Carta do topo da fundacao no momento: 4 OURO
		
		for (Naipe naipe: Naipe.values()) {
			for (int val = Carta.AS; val <= Carta.K; val++) {
				
				if (val == 5 && naipe == Naipe.OURO) continue;
				
				Carta cartaTest = new Carta(val, naipe);
				assertFalse("Fundacao ("+fundacao+") não deveria aceitar a carta "+cartaTest, fundacao.receberCarta(cartaTest));
			}
		}
		
		assertFalse("Fundacao ("+fundacao+") não deveria aceitar null ", fundacao.receberCarta(null));
	}
	
	@Test
	public void receberCartaDescarteTest() {
		Pilha descarte = new Descarte();
		
		Carta cartaTrue, cartaFalse;
		
		for (Naipe naipe: Naipe.values()) {
			for (int val = Carta.AS; val <= Carta.K; val++) {
				cartaTrue = new Carta(val,naipe);
				cartaTrue.virarCarta();
				assertTrue("Descarte ("+descarte+") deveria receber carta "+cartaTrue, descarte.receberCarta(cartaTrue));
			}
		}
		
		for (Naipe naipe: Naipe.values()) {
			for (int val = Carta.AS; val <= Carta.K; val++) {
				cartaFalse = new Carta(val,naipe);
				assertFalse("Descarte ("+descarte+") não deveria receber carta "+cartaFalse, descarte.receberCarta(cartaFalse));
			}
		}
	}
}
