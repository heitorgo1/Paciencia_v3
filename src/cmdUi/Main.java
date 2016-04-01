package cmdUi;

public class Main {

	public static void main (String[] args) {
		CmdView cmdview = new CmdView();
		cmdview.start();
	}
	
	public static void print (Object e) {
		System.out.print(e);
	}
}
