package Controller;

import model.Mesa;
import util.Baralho;
import util.Observer;

public class Controller implements Observer{

	private Mesa mesa;
	
	public Controller() {
		iniciarJogo();
	}
	
	public void iniciarJogo() {
		mesa = new Mesa(new Baralho(true));
		update();
	}
	
	@Override
	public void update() {
		
	}

}
