package cmdUi.menu;

import java.time.Duration;

import Controller.Controller;
import cmdUi.Main;
import cmdUi.PilhaListView;

public class MainMenu extends AbstractMenu {

	private PilhaListView plv;
	
	public MainMenu(Controller con, int options, AbstractMenu parent) {
		super(con, options, parent);
		this.plv = new PilhaListView(con.getPilhas());
	}

	@Override
	public void draw() {
		
		if (con.isVencedor()){
			Main.print("\n\nPARABÉNS! VOCÊ VENCEU!!! \n\n");
			Duration time = con.gameDuration();
			Main.print("\n\nTempo de jogo: "+time.toMinutes()+" minutos e "+time.getSeconds()%60+" segundos.\n\n");
			con.finalizar();
			return;
		}
		
		plv.drawPilhas();
		
		Main.print("\n");
		Main.print("1 - MOVER CARTAS\n");
		Main.print("2 - VIRAR CARTA ESTOQUE\n");
		Main.print("3 - MUDAR CONFIGURACAO DE ESTOQUE\n");
		Main.print("4 - REINICIAR\n");
		Main.print("5 - FINALIZAR\n");
				
	}
	
	public AbstractMenu processInput(int op) {
		switch (op) {
		case 1: opMoverCartas(); break;
		case 2: opVirarCartaEstoque(); break;
		case 3: return new ConfigMenu(con, 2, this);
		case 4: opReiniciar(); break;
		case 5: opFinalizar(); break;
		}
		return this;
	}
	
	public void opFinalizar() {
		Main.print("\n\nJogo finalizado.\n\n");
		System.exit(0);
	}
	
	public void opReiniciar() {
		con.iniciarJogo();
		this.plv.setPilhas(con.getPilhas());
	}
	
	public void opVirarCartaEstoque() {
		con.puxarCartaEstoque();
	}

	public void opMoverCartas() {
		Main.print("\n");
		Main.print("Pilha origem: ");
		int origem = sc.nextInt();
		Main.print("Pilha destino: ");
		int destino = sc.nextInt();
		
		if (origem == 2 || (origem >= 3 && origem <= 6) || (destino >= 3 && destino <= 6)) {
			con.moverCartaTopo(origem, destino);
		} else if (origem != destino) {
			Main.print("Valor da carta referencia: ");
			int valor = sc.nextInt();
			con.moverCartas(origem, destino, valor);
		} else {
			con.virarCartaFileira(origem-7);
		}
	}
	
	
	public void setPilhaListView (PilhaListView plv) {
		this.plv = plv;
	}

}
