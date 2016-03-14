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
	 * @return an fidelity card (object) of the class giving in paramater
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static FidelityStrategy create(String s) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		return (FidelityStrategy) Class.forName(s).getConstructor().newInstance();
	}
}
