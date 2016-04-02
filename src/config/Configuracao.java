package config;

/**Configurações gerais do jogo.
 * Padrão Singleton*/
public class Configuracao {

	public static final int PUXAR_UMA_CARTA = 0;
	public static final int PUXAR_TRES_CARTAS = 1;
	
	/**Representa o modo como as cartas serão puxadas do estoque*/
	private int modoPuxarCartasEstoque; 
	
	private static Configuracao config = null;
	
	private Configuracao () {
		modoPuxarCartasEstoque = PUXAR_UMA_CARTA;
	}
	
	public static Configuracao getInstance() {
		if (config == null) config = new Configuracao();
		return config;
	}
	
	public void setModoEstoque(int modo) {
		modoPuxarCartasEstoque = modo;
	}
	
	public int getQtdCartasPuxadasEstoque() {
		switch (modoPuxarCartasEstoque) {
			case PUXAR_UMA_CARTA: return 1;
			case PUXAR_TRES_CARTAS: return 3;
			default: return 1;
		}
	}
}
