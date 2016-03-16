import java.util.ArrayList;
import java.util.Date;
/**
 * Class for the orders of a customer
 */
public class Order {
	private ArrayList<Meal> meals;
	private Date date;
	private float price;
	private Customer customer;
	
	/**
	 * Order constructor
	 * @param c the customer initiating the order
	 */
	public Order(Customer c){
		this.meals = new ArrayList<Meal>();
		this.date = new Date();
		this.customer = c;
	}
	/**
	 * Get the meals in the order
	 * @return the meals in the order
	 */
	public ArrayList<Meal> getMeals() {
		return meals;
	}
	/**
	 * Set the meals in the order
	 * @param meals the meals to set in the order
	 */
	public void setMeals(ArrayList<Meal> meals) {
		this.meals = meals;
	}
	/**
	 * Add a meal to the order
	 * @param meal the meal to add in the order
	 */
	public void addMeal(Meal meal){
		this.meals.add(meal);
	}
	/**
	 * Get the date at which the order is being done
	 * @return the date at which the order is being done
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * Set the date at which the order is being done
	 * @param date the date at which the order is being done
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * Get the price of the order
	 * @return the price of the order
	 */
	public float getPrice() {
		return price;
	}
	/**
	 * Set the price of the order
	 * @param price the price of the order
	 */
	public void setPrice(float price) {
		this.price = price;
	}
	/**
	 * Get the customer making the order
	 * @return the customer making the order
	 */
	public Customer getCustomer() {
		return customer;
	}
	/**
	 * Set the customer making the order
	 * @param c the customer making the order
	 */
	public void setCustomer(Customer c) {
		this.customer = c;
	}
	
	/**
	 * 
	 * @param ingredientName the name of the ingredient to personalize in the meal
	 * @param quantity the quantity we want to add or remove
	 * @param index the index of the meal in order
	 */
	public void personalizeMealByIndex(String ingredientName, Integer personalization, Integer index){
		if(index>=0 && index < meals.size()){
			Meal mealToCustom = meals.get(index);
			mealToCustom.personalize(ingredientName, personalization);
			meals.set(index, mealToCustom);
		}	
	}
}
