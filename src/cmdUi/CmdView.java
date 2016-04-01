package cmdUi;

import java.util.Scanner;

import Controller.Controller;
import util.View;

public class CmdView implements View{

	private Controller con;
	private PilhaListView plv;
	
	private static final int MAIN_MENU = 0;
	private static final int CONFIG_MENU = 1;
	
	private static int menu_id = MAIN_MENU;
	
	public CmdView() {
		con = new Controller(this);
		plv = new PilhaListView(con.getPilhas());
	}
	
	public void start() {
		boolean inGame = true;
		
		drawOnScreen();
		
		while (inGame) {
			if (con.isVencedor()) {
				Main.print("\n\nPARABENS, VOCE VENCEU!\n\n");
				break;
			}
			getInput();
		}
	}
	
	public void drawMainMenu() {
		Main.print("\n");
		Main.print("1 - MOVER CARTAS\n");
		Main.print("2 - VIRAR CARTA ESTOQUE\n");
		Main.print("3 - MUDAR CONFIGURACAO DE ESTOQUE\n");
		Main.print("4 - REINICIAR\n");
		Main.print("5 - FINALIZAR\n");
	}
	
	public void getMainMenuInput(Scanner sc) {
		switch (sc.nextInt()) {
		case 1: 
			
			Main.print("Pilha origem: ");
			int origem = sc.nextInt();
			Main.print("Pilha destino: ");
			int destino = sc.nextInt();
			
			if (origem == 2 || (origem >= 3 && origem <= 6)) {
				con.moverCartaTopo(origem, destino);
			}else if (origem != destino) {
				Main.print("Valor da carta referencia: ");
				int valor = sc.nextInt();
				con.moverCartas(origem, destino, valor);
			} else {
				con.virarCartaFileira(origem-7);
			}
			
			break;
		case 2: con.puxarCartaEstoque(); break;
		case 3: menu_id = CONFIG_MENU; break;
		case 4: 
			con.iniciarJogo(); 
			plv = new PilhaListView(con.getPilhas()); 
			drawOnScreen(); 
			break;
		case 5: con.finalizar(); break;
		
		}
	}
	
	public void drawConfigMenu() {
		Main.print("\n");
		Main.print("1 - PUXAR UMA CARTA DO ESTOQUE\n");
		Main.print("2 - PUXAR TRES CARTAS DO ESTOQUE\n");
	}
	
	public void getConfigMenuInput(Scanner sc) {
		switch (sc.nextInt()) {
		case 1: con.setPuxarUmaCartaEstoque(); break;
		case 2: con.setPuxarTresCartasEstoque(); break;
		}
		menu_id = MAIN_MENU;
		drawOnScreen();
	}
	
	public void getInput() {	
		switch (menu_id) {
		case MAIN_MENU: drawMainMenu(); break;
		case CONFIG_MENU: drawConfigMenu(); break;
		}
		getMenuInput();
	}
	
	public void getMenuInput() {
		Scanner sc = new Scanner(System.in);
		
		switch(menu_id) {
		case MAIN_MENU: getMainMenuInput(sc); break;
		case CONFIG_MENU: getConfigMenuInput(sc); break;
		}
		
	}
	
	@Override
	public void drawOnScreen() {
		plv.drawPilhas();
	}

}
