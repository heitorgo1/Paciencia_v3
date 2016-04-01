package Controller;

import java.util.ArrayList;

import config.Configuracao;
import model.Mesa;
import model.Pilha;
import util.Baralho;
import util.Observer;
import util.View;

public class Controller implements Observer{

	private Mesa mesa;
	private View view;
	
	public Controller(View view) {
		this.view = view;
		iniciarJogo();
	}
	
	public void iniciarJogo() {
		mesa = new Mesa(new Baralho(true));
		mesa.addObserver(this);
	}
	
	public boolean moverCartas(int origem, int destino, int valorReferencia) {
		return mesa.moverCartas(origem, destino, valorReferencia);
	}
	
	public boolean moverCartaTopo(int origem, int destino) {
		return mesa.moverCartaTopo(origem, destino);
	}
	
	public boolean isVencedor() {
		return mesa.verificarVencedor();
	}
	
	public void virarCartaFileira(int index) {
		mesa.virarCartaFileira(index);
	}
	
	public void puxarCartaEstoque() {
		mesa.puxarCartasEstoque();
	}
	
	public void setPuxarTresCartasEstoque() {
		Configuracao.getInstance().setModoEstoque(Configuracao.PUXAR_TRES_CARTAS);
	}
	
	public void setPuxarUmaCartaEstoque() {
		Configuracao.getInstance().setModoEstoque(Configuracao.PUXAR_UMA_CARTA);
	}
	
	public void finalizar(){
		System.exit(0);
	}
	
	public ArrayList<Pilha> getPilhas() {
		return mesa.getPilhas();
	}
	
	
	@Override
	public void update() {
		view.drawOnScreen();
	}

}
