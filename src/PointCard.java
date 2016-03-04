import java.io.Serializable;

public class PointCard implements FidelityStrategy, Serializable{
	private float points;
	PointCard(){
		this.points=0;
	}
	
	@Override
	public float pay(Order o) {
		float price = getPrice(o);
		if(points>100){
			points-=100;
		}
		points+=price/10;
		return price;
	}

	@Override
	public float getPrice(Order o) {
		float price = 0;
		for(Meal m : o.getMeals()){
			price+=m.getPrice();
		}
		if(points>100){
			price*=0.9;
		}
		return price;
	}
	
}
