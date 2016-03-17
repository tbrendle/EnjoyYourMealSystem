import java.lang.reflect.InvocationTargetException;

/**
 * 
 * Factory to create the fidelity cards
 * 
 */
public class CardFactory {
	/**
	 * 
	 * @param s name of the card
	 * @return an fidelity card (object) of the class given as a paramater
	 */
	public static FidelityStrategy create(String s){
		try {
			return (FidelityStrategy) Class.forName(s).getConstructor().newInstance();
		}
		catch(Exception e) {
			throw new IllegalArgumentException("Can't create fidelity card for string : " + s);
		}
	}
}
