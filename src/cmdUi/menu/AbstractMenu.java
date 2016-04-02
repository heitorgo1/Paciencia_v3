package cmdUi.menu;

import java.util.Scanner;

import Controller.Controller;
import cmdUi.Main;

/**Tela de menu em Linha de Comando*/
public abstract class AbstractMenu {

	/**Controller para realizar ações do jogo Paciência*/
	protected Controller con;
	/**Menu que chamou esse menu*/
	protected AbstractMenu parent;
	/**Quantidade de opções possíveis*/
	protected int options;
	/**Scanner para entrada de dados*/
	protected Scanner sc;
	
	protected AbstractMenu(Controller con, int options, AbstractMenu parent) {
		this.con = con;
		this.parent = parent;
		this.options = options;
		sc = new Scanner(System.in);
	}
	
	public abstract void draw();
		
	public abstract AbstractMenu processInput(int op);
	
	/**Checar se uma opção é válida.
	 * @param option	Valor da opção
	 * @return Se opção é válida ou não*/
	public boolean isOptionValid(int option) {
		return (option >= 1 && option <= options);
	}
	
	/**Receber uma escolha de opção do usuário
	 * @return Opção entrada*/
	public int getOptionInput() {
		int op = sc.nextInt();
		
		while (!isOptionValid(op)) {
			Main.print("\nInsira uma opção válida.\n");
			op = sc.nextInt();
		}
		
		return op;		
	}
}
