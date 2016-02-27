
public class LotteryCard implements FidelityStrategy {

	@Override
	public float pay(Order o) {
		if(Math.random()>0.99)
			return 0;
		return getPrice(o);
	}

	@Override
	public float getPrice(Order o) {
		float price = 0;
		for(Meal m : o.getMeals()){
			price+=m.getPrice();
		}
		return price;
	}
	
}