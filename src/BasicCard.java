import java.io.Serializable;

public class BasicCard implements FidelityStrategy, Serializable {
	@Override
	public float pay(Order o) {
		return getPrice(o);
	}

	@Override
	public float getPrice(Order o) {
		float price = 0;
		for(Meal m : o.getMeals()){
			if(m.isPromotion()){
				price+=m.getSpecialPrice()+m.getExtraPrice();
			} else {
				price+=m.getPrice()+m.getExtraPrice();
			}
		}
		return price;
	}

}
