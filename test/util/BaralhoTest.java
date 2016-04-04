package util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class BaralhoTest {

	@Test
	public void baralhoNaoVazioTest() {
		Baralho baralho = new Baralho();
		assertFalse("Baralho deveria estar com cartas", baralho.isEmpty());
	}
	
	@Test
	public void baralhoVazioTest() {
		Baralho baralho = new Baralho();
		baralho.puxarTodasAsCartas();
		assertTrue("Baralho deveria estar vazio", baralho.isEmpty());
	}
	
	@Test
	public void cartaTopoReiTest() {
		Baralho baralho = new Baralho();
		ArrayList<Carta> cartasPuxadas = baralho.puxarCartas(1);
		Carta topo = cartasPuxadas.get(0);
		assertEquals("Carta do topo deveria ser K", Carta.K, topo.getValor());
	}

	@Test
	public void puxarTresCartasTopoTest() {
		Baralho baralho = new Baralho();
		ArrayList<Carta> cartasPuxadas = baralho.puxarCartas(3);
		assertEquals("Deveria ter puxado 3 cartas", 3, cartasPuxadas.size());
	}
	
	@Test
	public void puxarTodasAsCartasTest() {
		Baralho baralho = new Baralho();
		ArrayList<Carta> cartasPuxadas = baralho.puxarTodasAsCartas();
		assertEquals("Deveria ter puxado 52 cartas", 52, cartasPuxadas.size());
		assertEquals("A primeira carta puxada deveria ser um K", Carta.K, cartasPuxadas.get(0).getValor());
	}
	
	@Test
	public void puxarMaisDe52CartasTopoTest() {
		Baralho baralho = new Baralho();
		ArrayList<Carta> cartasPuxadas = baralho.puxarCartas(54);
		assertEquals("Deveria ter puxado apenas 52 cartas", 52, cartasPuxadas.size());
		assertEquals("A primeira carta puxada deveria ser um K", Carta.K, cartasPuxadas.get(0).getValor());
		assertEquals("A última carta puxada deveria ser um AS",Carta.AS, cartasPuxadas.get(51).getValor());

	}
	
}
