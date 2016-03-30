package model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import util.Carta;
import util.Naipe;

public class PilhaTest {

	public ArrayList<Carta> cartasParaFileiraAceitar() {
		ArrayList<Carta> cartas = new ArrayList<>();
		
		cartas.add(new Carta(Carta.K, Naipe.ESPADA));
		cartas.add(new Carta(Carta.Q, Naipe.COPAS));
		cartas.add(new Carta(Carta.J, Naipe.PAUS));
		cartas.add(new Carta(10, Naipe.OURO));
		
		cartas.get(0).virarCarta(); 
		cartas.get(1).virarCarta(); 
		cartas.get(2).virarCarta(); 
		cartas.get(3).virarCarta(); 
		
		return cartas;
	}
	
	public ArrayList<Carta> cartasParaFileiraRejeitar() {
		ArrayList<Carta> cartas = new ArrayList<>();
		
		cartas.add(new Carta(Carta.K, Naipe.ESPADA));
		cartas.add(new Carta(Carta.Q, Naipe.PAUS));
		cartas.add(new Carta(Carta.J, Naipe.ESPADA));
		cartas.add(new Carta(10, Naipe.OURO));
		
		cartas.get(0).virarCarta(); 
		cartas.get(1).virarCarta(); 
		cartas.get(2).virarCarta(); 
		cartas.get(3).virarCarta(); 
		
		return cartas;
	}
	
	public ArrayList<Carta> cartasParaFundacaoAceitar() {
		ArrayList<Carta> cartas = new ArrayList<>();
		
		cartas.add(new Carta(Carta.AS, Naipe.ESPADA));
		cartas.add(new Carta(2, Naipe.ESPADA));
		cartas.add(new Carta(3, Naipe.ESPADA));
		cartas.add(new Carta(4, Naipe.ESPADA));
		
		cartas.get(0).virarCarta(); 
		cartas.get(1).virarCarta(); 
		cartas.get(2).virarCarta(); 
		cartas.get(3).virarCarta(); 
		
		return cartas;
	}
	
	public ArrayList<Carta> cartasParaFundacaoRejeitar() {
		ArrayList<Carta> cartas = new ArrayList<>();
		
		cartas.add(new Carta(Carta.AS, Naipe.ESPADA));
		cartas.add(new Carta(2, Naipe.COPAS));
		cartas.add(new Carta(3, Naipe.PAUS));
		cartas.add(new Carta(4, Naipe.OURO));
		
		cartas.get(0).virarCarta(); 
		cartas.get(1).virarCarta(); 
		cartas.get(2).virarCarta(); 
		cartas.get(3).virarCarta(); 
		
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
		ArrayList<Carta> cartas = cartasParaFileiraAceitar();
		
		for (Carta carta: cartas) {
			assertTrue("Pilha ("+fileira+") deveria aceitar a carta "+carta, fileira.receberCarta(carta));
		}
	}
	
	@Test
	public void receberCartaFileiraFalseTest() {
		Pilha fileira = new Fileira();
		ArrayList<Carta> cartas = cartasParaFileiraAceitar();
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
		ArrayList<Carta> cartas = cartasParaFundacaoAceitar();
		
		for (Carta carta: cartas) {
			assertTrue("Fundacao ("+fundacao+") deveria aceitar a carta "+carta, fundacao.receberCarta(carta));
		}
	}
	
	@Test
	public void receberCartaFundacaoFalseTest() {
		Pilha fundacao = new Fundacao();
		ArrayList<Carta> cartas = cartasParaFundacaoAceitar();
		
		
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
		
		Carta cartaFalse;
		
		for (Naipe naipe: Naipe.values()) {
			for (int val = Carta.AS; val <= Carta.K; val++) {
				cartaFalse = new Carta(val,naipe);
				assertFalse("Descarte ("+descarte+") não deveria receber carta "+cartaFalse, descarte.receberCarta(cartaFalse));
			}
		}
	}
	
	@Test
	public void receberMultiplasCartasFileiraTrueTest() {
		Pilha fileira = new Fileira();
		
		assertTrue(fileira.receberCartas(cartasParaFileiraAceitar()));
	}
	
	@Test
	public void receberMultiplasCartasFileiraFalseTest() {
		Pilha fileira = new Fileira();
		
		assertFalse(fileira.receberCartas(cartasParaFileiraRejeitar()));
	}
	
	@Test
	public void receberMultiplasCartasFundacaoTrueTest() {
		Pilha fundacao = new Fundacao();
		
		assertTrue(fundacao.receberCartas(cartasParaFundacaoAceitar()));
	}
	
	@Test
	public void receberMultiplasCartasFundacaoFalseTest() {
		Pilha fundacao = new Fundacao();
		
		assertFalse(fundacao.receberCartas(cartasParaFundacaoRejeitar()));
	}
}
