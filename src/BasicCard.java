import java.io.Serializable;

/**
 * 
 * Basic card : one of the fidelity card
 *
 */
public class BasicCard implements FidelityStrategy, Serializable {
	/**
	 * Pay and order using a basic card
	 * @param o order we want to pay
	 * @return the price we have to pay
	 */
	@Override
	public float pay(Order o) {
		return getPrice(o);
	}

	/**
	 * Getting the price of and order, having a basic card
	 * @param o order we want to pay
	 * @return the price of the order
	 */
	@Override
	public float getPrice(Order o) {
		float price = 0;
		for(Meal m : o.getMeals()){
			// With a basic card, we have access to the promotion
			if(m.isPromotion()){
				price+=m.getSpecialPrice()+m.getExtraPrice();
			} else {
				price+=m.getPrice()+m.getExtraPrice();
			}
		}
		return price;
	}
	
	/**
	 * @return Hard Copy of the object
	 */
	@Override
	public FidelityStrategy clone(){
		return new BasicCard();
	}

}
