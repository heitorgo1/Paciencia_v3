package cmdUi;

import Controller.Controller;
import cmdUi.menu.AbstractMenu;
import cmdUi.menu.MainMenu;
import util.View;

/**Cuida do controle entre qual tela de menu deve ser mostrada a cada momento.*/
public class CmdView implements View{

	/**Controller do jogo Paciência*/
	private Controller con;
	
	/**Tela atual com menu*/
	private AbstractMenu currentMenu;
	
	public CmdView() {
		con = new Controller(this);
		currentMenu = new MainMenu(con,5,null);
	}
	
	/**Inicia o loop onde a tela é desenhada, o sistema espera uma entrada, e logo depois, realiza uma ação.*/
	public void start() {
		
		while (true) {
			currentMenu.draw();
			int op = currentMenu.getOptionInput();
			currentMenu = currentMenu.processInput(op);
		}
	}

	@Override
	public void drawOnScreen() {}
}
