package core;
import java.io.Serializable;

/**
 * 
 * Lottery card : one of the fidelity card
 *
 */
public class LotteryCard implements FidelityStrategy, Serializable{

	/**
	 * Pay and order using a lottery card
	 * @param o order we want to pay
	 * @return the price we have to pay
	 */
	@Override
	public float pay(Order o) {
		if(Math.random()>0.99)
			return 0;
		return getPrice(o);
	}

	/**
	 * Getting the price of and order, having a lottery card
	 * @param o order we want to pay
	 * @return the price of the order
	 */
	@Override
	public float getPrice(Order o) {
		float price = 0;
		for(Meal m : o.getMeals()){
			price+=m.getPrice()+m.getExtraPrice();
		}
		return price;
	}
	
	/**
	 * @return Hard Copy of the object
	 */
	@Override
	public FidelityStrategy clone(){
		return new LotteryCard();
	}
}
