package model;

import java.util.ArrayList;

import config.Configuracao;
import util.Baralho;
import util.Carta;
import util.Observable;
import util.Observer;


/**Representa uma mesa do jogo Paciência, com pilhas e funções
 * de movimentação para as cartas nas pilhas.*/
public class Mesa implements Observable {

	/**Pilhas de cartas*/
	private ArrayList<Pilha> pilhas;
	
	/**Quantidade inicial do estoque*/
	private static final int QTD_ESTOQUE_INICIAL = 52-1-2-3-4-5-6-7; //Quantidade de cartas do estoque inicialmente é igual a 52 menos as cartas das fileiras
	
	/**Estados do possíveis de um jogo de Paciência*/
	public enum GameStatus {
		VENCIDO, JOGANDO, IMPASSE;
	};
	
	private Verificador verificador = new Verificador();
	
	/**Observadores dessa classe*/
	private ArrayList<Observer> observers = new ArrayList<>();
	
	/**Gera as pilhas de acordo com as regras do jogo.
	 * @param baralho	Fonte de cartas para construir Estoque e Fileiras*/
	public Mesa(Baralho baralho) {
		pilhas = new ArrayList<>();
				
		pilhas.add(new Estoque(baralho.puxarCartas(QTD_ESTOQUE_INICIAL)));
		pilhas.add(new Descarte());
		
		//4 fundações
		for (int i = 0; i < 4; i++) pilhas.add(new Fundacao());
		
		//7 fileiras
		for (int i = 1; i <= 7; i++)  {
			Pilha fileira = new Fileira(baralho.puxarCartas(i));
			fileira.cartaTopo().virarCarta();
			pilhas.add(fileira);
		}
		
	}
	
	/**Move as cartas do estoque para o descarte de acordo com a configuração escolhida pelo usuário.*/
	public void puxarCartasEstoque() {
		int qtd = Configuracao.getInstance().getQtdCartasPuxadasEstoque();
		
		ArrayList<Carta> cartasPassadas = new ArrayList<>();
		Carta cartaPuxada;
		
		Pilha estoque = getEstoque();
		Pilha descarte = getDescarte();
		
		if (estoque.isEmpty()) {
			
			//Tira todas as cartas do descarte e devolve ao estoque
			while ( (cartaPuxada = descarte.puxarCartaTopo()) != null) {
				cartaPuxada.virarCarta();
				cartasPassadas.add(cartaPuxada);
			}	
			
			estoque.inserirCartas(cartasPassadas);
			
		} else {
			
			while (qtd-- > 0 && (cartaPuxada =  estoque.puxarCartaTopo()) != null) {
				cartaPuxada.virarCarta();
				cartasPassadas.add(cartaPuxada);
			}
			
			descarte.inserirCartas(cartasPassadas);
		}
		
		notifyAllObservers();
		return;
	}
	
	
	/**Vira a carta do topo de uma Fileira caso ela esteja virada para baixo.
	 * @param index		Index da fileira escolhida*/
	public void virarCartaFileira(int index) {
		
		Pilha fileira = getFileira(index);
		
		Carta topo = fileira.cartaTopo();
		
		if (!topo.isParaCima()) topo.virarCarta();
		
		notifyAllObservers();
	}
	
	/**Move uma carta do topo de uma pilha para outra.
	 * @param fonte_ind		Index da pilha fonte
	 * @param destino_ind	Index da pilha destino
	 * @return 	Confirmação do movimento*/
	public boolean moverCartaTopo(int fonte_ind, int destino_ind) {
		Pilha fonte = getPilha(fonte_ind);
		Pilha destino = getPilha(destino_ind);
		
		Carta cartaPuxada = fonte.puxarCartaTopo();
		
		if (cartaPuxada == null) return false;
		
		boolean res = false;
		
		if (destino.receberCarta(cartaPuxada)) res = true;
		else {
			fonte.inserirCarta(cartaPuxada);
			res = false;
		}
		
		notifyAllObservers();
		return res;
	}
	
	/**Move várias cartas de uma pilha para outra a partir de uma carta de referência.
	 * @param fonte_ind		Index da pilha fonte
	 * @param destino_ind	Index da pilha destino
	 * @param valorCartaReferencia		Valor da primeira carta do grupo de cartas a ser movido
	 * @return 	Confirmação do movimento*/
	public boolean moverCartas (int fonte_ind, int destino_ind, int valorCartaReferencia) {
		Pilha fonte = getPilha(fonte_ind);
		Pilha destino = getPilha(destino_ind);
		
		ArrayList<Carta> cartasPuxadas = fonte.puxarAPartirDeCarta(valorCartaReferencia);
		
		if (cartasPuxadas == null || cartasPuxadas.size() <= 0) return false;
		
		boolean res = false;
		
		if (destino.receberCartas(cartasPuxadas)) res = true;
		else {
			fonte.inserirCartas(cartasPuxadas);
			res = false;
		}
		
		notifyAllObservers();
		return res;
	}
	
	public GameStatus getGameStatus() {
		return verificador.verificarSituacao();
	}

	public Pilha getEstoque() {
		return pilhas.get(0);
	}
	
	public Pilha getDescarte() {
		return pilhas.get(1);
	}
	
	public Pilha getFundacao(int index) {
		return pilhas.get(index+2);
	}
	
	public Pilha getFileira(int index) {
		return pilhas.get(index+6);
	}
	
	public Pilha getPilha(int index) {
		return pilhas.get(index-1);
	}
	
	public ArrayList<Pilha> getPilhas(){
		return pilhas;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		int index = 1;
		for (Pilha pilha: pilhas) {
			sb.append((index++)+" - ");
			sb.append(pilha.getClass().getName().toUpperCase().split("[.]")[1]);
			sb.append(" == ");
			sb.append(pilha);
			sb.append("\n");
		}
		
		return sb.toString();
	}

	@Override
	public void addObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	@Override
	public void notifyAllObservers() {
		for (Observer observer: observers) {
			observer.update();
		}
	}
	
	/**Classe que faz verificações sobre o estado do jogo*/
	private class Verificador {
		
		/**Verifica a situação do jogo.
		 * @return Estado do jogo*/
		public GameStatus verificarSituacao() {
			if (verificarJogoVencido()) return GameStatus.VENCIDO;
			else return GameStatus.JOGANDO;
		}
		
		/**Verifica se o jogador já venceu a partida.
		 * @return Booleano representando se o jogador já venceu ou não*/
		private boolean verificarJogoVencido() {
			for (int i = 0; i < 4; i++) {
				Pilha fundacao = getFundacao(i);
				Carta topo = fundacao.cartaTopo();
				if (topo == null) return false;
				if (!topo.isMaiorValor()) return false;
			}
			return true;
		}

	}
}
