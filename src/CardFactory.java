import java.lang.reflect.InvocationTargetException;

public class CardFactory {
	public static FidelityStrategy create(String s) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		return (FidelityStrategy) Class.forName(s).getConstructor().newInstance();
	}
}
