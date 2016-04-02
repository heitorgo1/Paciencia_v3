package model;

import static org.junit.Assert.*;


import org.junit.Test;

import config.Configuracao;
import util.Baralho;
import util.Carta;
import util.Naipe;

public class MesaTest {
	
	public void quantidadeCartasTest(Pilha pilha, int quantidade) {
		assertEquals(pilha.getClass().getName()+" deveria possuir "+quantidade+" carta(s)", quantidade, pilha.cartas.size());
	}
	
	public void checarCartaTest(String expected, Carta actual) {
		assertEquals("A carta deveria ser "+expected, expected, actual.toString());
	}
	
	

	@Test
	public void virarCartaEstoqueTest() {
		Mesa mesa = new Mesa(new Baralho());
		Pilha estoque = mesa.getEstoque();
		Pilha descarte = mesa.getDescarte();
		
		Configuracao.getInstance().setModoEstoque(Configuracao.PUXAR_UMA_CARTA);
		
		quantidadeCartasTest(descarte, 0);
		quantidadeCartasTest(estoque, 24);
		
		mesa.puxarCartasEstoque();
				
		quantidadeCartasTest(descarte, 1);
		quantidadeCartasTest(estoque, 23);
		
		mesa.puxarCartasEstoque();
		
		quantidadeCartasTest(descarte, 2);
		quantidadeCartasTest(estoque, 22);
		
		Configuracao.getInstance().setModoEstoque(Configuracao.PUXAR_TRES_CARTAS);
		
		mesa.puxarCartasEstoque();
		
		quantidadeCartasTest(descarte, 5);
		quantidadeCartasTest(estoque, 19);
	}
	
	@Test
	public void virarTodasAsCartasEstoqueTest() {
		Mesa mesa = new Mesa(new Baralho());
		Pilha estoque = mesa.getEstoque();
		Pilha descarte = mesa.getDescarte();
		
		Configuracao.getInstance().setModoEstoque(Configuracao.PUXAR_TRES_CARTAS);
		
		//Retira todas as cartas do estoque
		for (int i = 0; i < 8; i++) {
			mesa.puxarCartasEstoque();
		}

		quantidadeCartasTest(estoque, 0);
		
		mesa.puxarCartasEstoque();

		quantidadeCartasTest(estoque, 24);
		quantidadeCartasTest(descarte, 0);
	}
	
	@Test
	public void moverCartaFileiraFundacaoTest() {
		Mesa mesa = new Mesa(new Baralho());
		Pilha fileira = mesa.getFileira(6); //Fileira (Pilha de index 13) com AS OURO no topo
		Pilha fundacao = mesa.getFundacao(0); //Fundacao (Pilha de index 3) vazia
		int fileira_ind = 13;
		int fundacao_ind = 3;
		

		quantidadeCartasTest(fileira, 7);
		quantidadeCartasTest(fundacao, 0);
		
		mesa.moverCartaTopo(fileira_ind, fundacao_ind);

		quantidadeCartasTest(fileira, 6);
		quantidadeCartasTest(fundacao, 1);
		
		checarCartaTest("AS OURO", fundacao.cartaTopo());
		checarCartaTest("[<>]", fileira.cartaTopo());
		
		//Não deve mover
		mesa.moverCartaTopo(fileira_ind,fundacao_ind);
		
		quantidadeCartasTest(fileira, 6);
		quantidadeCartasTest(fundacao, 1);
		
		fileira = mesa.getFileira(4); // Fileira (Pilha de index 11) com AS PAUS no topo
		
		fileira_ind = 11;
		
		mesa.moverCartaTopo(fileira_ind, fundacao_ind);
		
		quantidadeCartasTest(fileira, 5);
		quantidadeCartasTest(fundacao, 1);
		
		fundacao = mesa.getFundacao(1); //Fundacao (Pilha de index 4) vazia
		
		fundacao_ind = 4;
		
		mesa.moverCartaTopo(fileira_ind, fundacao_ind);
		
		quantidadeCartasTest(fileira, 4);
		quantidadeCartasTest(fundacao, 1);
		
		checarCartaTest("AS PAUS", fundacao.cartaTopo());
		checarCartaTest("[<>]", fileira.cartaTopo());
	}
	
	@Test
	public void moverCartaFundacaoFileiraTest() {
		Mesa mesa = new Mesa(new Baralho());
		Pilha fileira = mesa.getFileira(6); //Fileira (Pilha de index 13) com AS OURO no topo
		Pilha fundacao = mesa.getFundacao(0); //Fundacao (Pilha de index 3) vazia
		int fileira_ind = 13;
		int fundacao_ind = 3;
		
		mesa.moverCartaTopo(fileira_ind, fundacao_ind);
		
		fileira = mesa.getFileira(0); //Fileira (Pilha de index 7) com 2 ESPADA no topo
		
		fileira_ind = 7;
		
		mesa.moverCartaTopo(fundacao_ind, fileira_ind);
		
		checarCartaTest("AS OURO", fileira.cartaTopo());
		quantidadeCartasTest(fundacao, 0);
	}
	
	@Test
	public void moverCartaDescarteFileiraTest() {
		Mesa mesa = new Mesa(new Baralho());
		Pilha descarte = mesa.getDescarte(); // Pilha de index 2
		Pilha fileira = mesa.getFileira(0); //Fileira (Pilha de index 7) com 2 ESPADA no topo
		
		int descarte_ind = 2;
		int fileira_ind = 7;
		
		Configuracao.getInstance().setModoEstoque(Configuracao.PUXAR_TRES_CARTAS);
		
		mesa.puxarCartasEstoque();
		mesa.puxarCartasEstoque();
		mesa.puxarCartasEstoque();
		mesa.puxarCartasEstoque();
		
		//cartas do topo do descarte de 12 cartas: K ESPADA, AS COPAS
		
		//AS COPAS vai para a fileira com 2 ESPADA no topo
		mesa.moverCartaTopo(descarte_ind, fileira_ind);
		
		checarCartaTest("AS COPAS", fileira.cartaTopo());
		checarCartaTest("K ESPADA", descarte.cartaTopo());
		quantidadeCartasTest(descarte, 11);
		quantidadeCartasTest(fileira, 2);
	}
	
	@Test
	public void moverCartaDescarteFundacaoTest() {
		Mesa mesa = new Mesa(new Baralho());
		Pilha descarte = mesa.getDescarte(); // Pilha de index 2
		Pilha fundacao = mesa.getFundacao(0); //Fundação (Pilha de index 3) vazia
		
		int descarte_ind = 2;
		int fundacao_ind = 3;
		
		Configuracao.getInstance().setModoEstoque(Configuracao.PUXAR_TRES_CARTAS);
		
		mesa.puxarCartasEstoque();
		mesa.puxarCartasEstoque();
		mesa.puxarCartasEstoque();
		mesa.puxarCartasEstoque();
		
		//cartas do topo do descarte de 12 cartas: K ESPADA, AS COPAS
		
		mesa.moverCartaTopo(descarte_ind, fundacao_ind);
		
		checarCartaTest("AS COPAS", fundacao.cartaTopo());
		checarCartaTest("K ESPADA", descarte.cartaTopo());
		quantidadeCartasTest(descarte, 11);
		quantidadeCartasTest(fundacao, 1);
	}
	
	@Test
	public void moverMultiplasCartasFileiraTest() {
		Mesa mesa = new Mesa(new Baralho());
		Pilha fileira1 = mesa.getFileira(0); // Pilha index 7
		Pilha fileira2 = mesa.getFileira(1); // Pilha index 8
		
		int fileira1_ind = 7;
		int fileira2_ind = 8;
		
		// Limpa fileiras para customização
		fileira1.cartas.clear(); 
		fileira2.cartas.clear();
		
		//Fileira 1
		fileira1.cartas.push(new Carta(Carta.K, Naipe.ESPADA));
		fileira1.cartas.peek().virarCarta();
		fileira1.cartas.push(new Carta(Carta.Q, Naipe.COPAS));
		fileira1.cartas.peek().virarCarta();
		fileira1.cartas.push(new Carta(Carta.J, Naipe.PAUS));
		fileira1.cartas.peek().virarCarta();
		
		//Fileira 2
		fileira2.cartas.push(new Carta(10, Naipe.OURO));
		fileira2.cartas.peek().virarCarta();
		fileira2.cartas.push(new Carta(9, Naipe.PAUS));
		fileira2.cartas.peek().virarCarta();
		fileira2.cartas.push(new Carta(8, Naipe.COPAS));
		fileira2.cartas.peek().virarCarta();
		
		//Mover cartas da fileira 2 a partir da carta de valor 10
		mesa.moverCartas(fileira2_ind, fileira1_ind, 10);
		
		quantidadeCartasTest(fileira2, 0);
		quantidadeCartasTest(fileira1, 6);
		checarCartaTest("8 COPAS", fileira1.cartaTopo());
	}
}
