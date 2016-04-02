package cmdUi;

import Controller.Controller;
import cmdUi.menu.AbstractMenu;
import cmdUi.menu.MainMenu;
import util.View;

public class CmdView implements View{

	private Controller con;
	private AbstractMenu currentMenu;
	
	public CmdView() {
		con = new Controller(this);
		currentMenu = new MainMenu(con,5,null);
	}
	
	public void start() {
		
		while (true) {
			currentMenu.draw();
			int op = currentMenu.getOptionInput();
			currentMenu = currentMenu.processInput(op);
		}
	}

	@Override
	public void drawOnScreen() {
	}
}
