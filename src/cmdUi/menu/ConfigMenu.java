package cmdUi.menu;

import Controller.Controller;
import cmdUi.Main;

public class ConfigMenu extends AbstractMenu{
	
	public ConfigMenu(Controller con, int options, AbstractMenu parent) {
		super(con, options, parent);
	}

	@Override
	public void draw() {
		Main.print("\n");
		Main.print("1 - PUXAR UMA CARTA DO ESTOQUE\n");
		Main.print("2 - PUXAR TRES CARTAS DO ESTOQUE\n");
	}

	@Override
	public AbstractMenu processInput(int op) {
		switch (op) {
		case 1: con.setPuxarUmaCartaEstoque(); break;
		case 2: con.setPuxarTresCartasEstoque(); break;
		}
		return parent;
	}

}
