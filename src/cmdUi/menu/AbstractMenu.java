package cmdUi.menu;

import java.util.Scanner;

import Controller.Controller;
import cmdUi.Main;

public abstract class AbstractMenu {

	protected Controller con;
	protected AbstractMenu parent;
	protected int options;
	protected Scanner sc;
	
	protected AbstractMenu(Controller con, int options, AbstractMenu parent) {
		this.con = con;
		this.parent = parent;
		this.options = options;
		sc = new Scanner(System.in);
	}
	
	public abstract void draw();
		
	public abstract AbstractMenu processInput(int op);
	
	public boolean isOptionValid(int option) {
		return (option >= 1 && option <= options);
	}
	
	public int getOptionInput() {
		int op = sc.nextInt();
		
		while (!isOptionValid(op)) {
			Main.print("\nInsira uma opção válida.\n");
			op = sc.nextInt();
		}
		
		return op;		
	}
}
