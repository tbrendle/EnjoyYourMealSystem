package core;
import java.util.Observable;

public class GeneralNotifier extends Observable{
	
	/**
	 * Notify customers with a message
	 * @param message the message to send to the customers
	 */
	public void notifyAll(String message){
		notifyObservers(message);
	}
}
