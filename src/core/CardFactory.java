package core;
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
		if(s.equals("Basic"))
			return new BasicCard();
		else if (s.equals("Point"))
			return new PointCard();
		else if (s.equals("Lottery"))
			return new LotteryCard();
		else
			throw new IllegalArgumentException("Can't create fidelity card for string : " + s);
	}
}
