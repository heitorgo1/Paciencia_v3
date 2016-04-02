package Controller;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

import config.Configuracao;
import model.Mesa;
import model.Mesa.GameStatus;
import model.Pilha;
import util.Baralho;
import util.Observer;
import util.View;


/**Controlador que encapsula as possíveis ações do jogo e faz o controle das configurações.*/
public class Controller implements Observer{

	/**Mesa do jogo Paciência*/
	private Mesa mesa;
	/**Classe que representa a interface gráfica para o controller*/
	private View view;
	/**Momento de início do jogo*/
	private Instant start;
	/**Momento de fim do jogo*/
	private Instant end;
	
	public Controller(View view) {
		this.view = view;
		iniciarJogo();
	}
	
	/**Inicia o jogo criando uma nova mesa com baralho embaralhado e guardando o horário 
	 * de início.*/
	public void iniciarJogo() {
		start = Instant.now();
		mesa = new Mesa(new Baralho(true));
		mesa.addObserver(this);
	}
	
	/**Wrapper function para o moverCartas da mesa*/
	public boolean moverCartas(int origem, int destino, int valorReferencia) {
		return mesa.moverCartas(origem, destino, valorReferencia);
	}
	
	/**Wrapper function para o moverCartaTopo da mesa*/
	public boolean moverCartaTopo(int origem, int destino) {
		return mesa.moverCartaTopo(origem, destino);
	}
	
	/**Checa se o jogador já venceu e, caso afirmativo, guarda o momento de fim do jogo.*/
	public boolean isVencedor() {
		boolean res = mesa.getGameStatus()==GameStatus.VENCIDO;
		if (res) {
			end = Instant.now();
		}
		return res;
	}
	
	/**Wrapper function para o virarCartaFileira da mesa*/
	public void virarCartaFileira(int index) {
		mesa.virarCartaFileira(index);
	}
	
	/**Wrapper function para o puxarCartaEstoque da mesa*/
	public void puxarCartaEstoque() {
		mesa.puxarCartasEstoque();
	}
	
	/**Muda as configurações do jogo para puxar 3 cartas do estoque*/
	public void setPuxarTresCartasEstoque() {
		Configuracao.getInstance().setModoEstoque(Configuracao.PUXAR_TRES_CARTAS);
	}
	
	/**Muda as configurações do jogo para puxar 1 carta do estoque*/
	public void setPuxarUmaCartaEstoque() {
		Configuracao.getInstance().setModoEstoque(Configuracao.PUXAR_UMA_CARTA);
	}
	
	/**Finaliza o jogo*/
	public void finalizar(){
		System.exit(0);
	}
	
	/**Pega as pilhas para poderem ser visualizadas em uma interface
	 * @return Pilhas da mesa*/
	public ArrayList<Pilha> getPilhas() {
		return mesa.getPilhas();
	}
	
	
	/**Duração total do jogo.
	 * @return Duração do jogo*/
	public Duration gameDuration() {
		return Duration.between(start, end);
	}
	
	
	@Override
	public void update() {
		view.drawOnScreen();
	}

}
