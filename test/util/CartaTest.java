package util;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

public class CartaTest {

	@Test
	public void cartaParaBaixoTest() {
		Carta carta = new Carta(1, Naipe.COPAS);
		
		assertFalse("Carta deveria estar para baixo", carta.isParaCima());
		
		carta.virarCarta();
		carta.virarCarta();
		
		assertFalse("Carta deveria estar para baixo", carta.isParaCima());
	}
	
	@Test
	public void cartaParaCimaTest(){
		Carta carta = new Carta(Carta.AS, Naipe.OURO);
		
		carta.virarCarta();
		
		assertTrue("Carta deveria estar para cima", carta.isParaCima());
	}
	
	@Test
	public void cartaMaiorValorKTest(){
		Carta carta = new Carta(Carta.K, Naipe.PAUS);
		
		assertTrue("Carta deveria ser a de maior valor", carta.isMaiorValor());
	}
	
	@Test
	public void cartaMenorValorASTest() {
		Carta carta = new Carta(Carta.AS, Naipe.ESPADA);
		
		assertTrue("Carta deveria ser a de menor valor", carta.isMenorValor());
	}
	
	@Test
	public void cartaVermelhaTest() {
		Carta cartaCopas = new Carta(Carta.AS, Naipe.COPAS);
		Carta cartaOuro = new Carta (Carta.AS, Naipe.OURO);
		
		assertEquals("Carta deveria ser vermelha",Color.RED, cartaCopas.getColor());
		assertEquals("Carta deveria ser vermelha",Color.RED, cartaOuro.getColor());
	}

	
	@Test
	public void cartaPretaTest() {
		Carta cartaPaus = new Carta(Carta.K, Naipe.PAUS);
		Carta cartaEspada = new Carta (Carta.K, Naipe.ESPADA);
		
		assertEquals("Carta deveria ser preta",Color.BLACK, cartaPaus.getColor());
		assertEquals("Carta deveria ser preta",Color.BLACK, cartaEspada.getColor());
	}
	
	@Test
	public void cartaParaBaixoStringTest() {
		Carta carta = new Carta(Carta.AS, Naipe.PAUS);
		
		assertEquals("Carta deveria ser representada para baixo","[<>]", carta.toString());
		carta.virarCarta();
		carta.virarCarta();
		assertEquals("Carta deveria ser representada para baixo","[<>]", carta.toString());
	}
	
	@Test
	public void cartaParaCimaStringTest() {
		Carta cartaAs = new Carta(Carta.AS, Naipe.PAUS);
		Carta cartaRei = new Carta(Carta.K, Naipe.ESPADA);
		Carta carta2 = new Carta(2,Naipe.OURO);
		
		cartaAs.virarCarta();
		assertEquals("Carta deveria ser representada para cima","AS PAUS", cartaAs.toString());
		
		cartaRei.virarCarta();
		assertEquals("Carta deveria ser representada para cima","K ESPADA", cartaRei.toString());
		
		carta2.virarCarta();
		assertEquals("Carta deveria ser representada para cima","2 OURO", carta2.toString());
		
	}
}
