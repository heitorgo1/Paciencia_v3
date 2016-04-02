package model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.Mesa.GameStatus;
import util.Baralho;
import util.Carta;
import util.Naipe;

public class VerificadorTest {

	public ArrayList<Carta> fundacaoVencedor(Naipe naipe) {
		ArrayList<Carta> cartas = new ArrayList<>();
		
		for (int i = Carta.AS; i <= Carta.K; i++) {
			Carta carta = new Carta(i,naipe);
			carta.virarCarta();
			cartas.add(carta);
		}
	
		return cartas;
	}
	
	
	@Test
	public void verificarJogoVencidoTest() {
		Mesa mesa = new Mesa(new Baralho());
		
		mesa.getFundacao(0).receberCartas(fundacaoVencedor(Naipe.OURO));
		mesa.getFundacao(1).receberCartas(fundacaoVencedor(Naipe.ESPADA));
		mesa.getFundacao(2).receberCartas(fundacaoVencedor(Naipe.COPAS));
		mesa.getFundacao(3).receberCartas(fundacaoVencedor(Naipe.PAUS));
				
		assertTrue("O jogador deveria ter vencido", mesa.getGameStatus()==GameStatus.VENCIDO);
	}

	
	@Test
	public void verificarJogoNaoVencidoTest() {
		Mesa mesa = new Mesa(new Baralho());
		
		assertFalse("O jogador não deveria ter vencido", mesa.getGameStatus()==GameStatus.VENCIDO);
		
		mesa.getFundacao(0).receberCartas(fundacaoVencedor(Naipe.OURO));
		mesa.getFundacao(1).receberCartas(fundacaoVencedor(Naipe.ESPADA));
		mesa.getFundacao(2).receberCartas(fundacaoVencedor(Naipe.COPAS));
		
		
		assertFalse("O jogador não deveria ter vencido", mesa.getGameStatus()==GameStatus.VENCIDO);
	}
}
