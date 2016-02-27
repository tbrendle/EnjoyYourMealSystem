
public class BasicCard implements FidelityStrategy {
	@Override
	public float pay(Order o) {
		return getPrice(o);
	}

	@Override
	public float getPrice(Order o) {
		float price = 0;
		for(Meal m : o.getMeals()){
			if(m.isPromotion()){
				price+=m.getSpecialPrice();
			} else {
				price+=m.getPrice();
			}
		}
		return price;
	}

}
