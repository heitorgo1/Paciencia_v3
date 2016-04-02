package util;

/**Representa uma interface observável, a classe que a implementa pode notificar 
 * observadores sobre mudança nos dados*/
public interface Observable {

	void addObserver (Observer o);
	void removeObserver (Observer o);
	void notifyAllObservers ();
}
