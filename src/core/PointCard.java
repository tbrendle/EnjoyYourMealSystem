package core;
import java.io.Serializable;

/**
 * 
 * Point card : one of the fidelity card
 *
 */
public class PointCard implements FidelityStrategy, Serializable{
	private float points;
	/**
	 * PointCard constructor
	 */
	PointCard(){
		this.points=0;
	}
	
	/**
	 * Setter : sets the number of points of the PointCard
	 * @param points the number of points to associate to this card
	 */
	public void setPoint(float points){
		this.points = points;
	}
	
	/**
	 * Pay and order using a point card
	 * @param o order we want to pay
	 * @return the price we have to pay
	 */
	@Override
	public float pay(Order o) {
		float price = getPrice(o);
		if(points>100){
			points-=100;
		}
		points+=price/10;
		return price;
	}

	/**
	 * Getting the price of and order, having a point card
	 * @param o order we want to pay
	 * @return the price of the order
	 */
	@Override
	public float getPrice(Order o) {
		float price = 0;
		for(Meal m : o.getMeals()){
			price+=m.getPrice()+m.getExtraPrice();
		}
		if(points>100){
			price*=0.9;
		}
		return price;
	}
	
	
	/**
	 * Clones the instance of PointCard to a new instance with same characteristics (hard copy)
	 * @return a hard copy of the PointCard instance
	 */
	@Override
	public FidelityStrategy clone(){
		PointCard p = new PointCard();
		p.setPoint(points);
		return p;
	}
	
	@Override
	public String getName() {
		return "Point Card";
	}
	
}
