import java.util.ArrayList;
import java.util.Date;

public class Order {
	private ArrayList<Meal> meals;
	private Date date;
	private float price;
	public Order(){
		this.meals = new ArrayList<Meal>();
		this.date = new Date();
	}
	public ArrayList<Meal> getMeals() {
		return meals;
	}

	public void setMeals(ArrayList<Meal> meals) {
		this.meals = meals;
	}
	
	public void addMeal(Meal meal){
		this.meals.add(meal);
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
}
