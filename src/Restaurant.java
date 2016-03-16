import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class for the restaurants
 */
public class Restaurant implements Serializable {
	private HashMap<String,Customer> users;
	private HashMap<String,Admin> admins;
	private HashMap<String,Meal> meals;
	private ArrayList<Order> ordersHistory;
	/**
	 * Restaurant constructor
	 */
	public Restaurant(){
		this.setUsers(new HashMap<String,Customer>());
		this.setAdmins(new HashMap<String,Admin>());
		this.setMeals(new HashMap<String,Meal>());
		this.setOrdersHistory(new ArrayList<Order>());
	}
	/**
	 * Get the history of orders in this restaurant
	 * @return the history of orders in this restaurant
	 */
	public ArrayList<Order> getOrdersHistory() {
		return ordersHistory;
	}
	/**
	 * Set the history of orders in this restaurant
	 * @param ordersHistory the history of orders in this restaurant to set
	 */
	public void setOrdersHistory(ArrayList<Order> ordersHistory) {
		this.ordersHistory = ordersHistory;
	}
	/**
	 * Add an order made in this restaurant
	 * @param o the order made in this restaurant
	 */
	public void addOrder(Order o){
		this.ordersHistory.add(o);
	}
	/**
	 * Get the meals available in this restaurant
	 * @return the meals available in this restaurant
	 */
	public HashMap<String,Meal> getMeals() {
		return meals;
	}
	/**
	 * Set the meals available in this restaurant
	 * @param meals the meals available in this restaurant
	 */
	public void setMeals(HashMap<String,Meal> meals) {
		this.meals = meals;
	}
	/**
	 * Add a meal available in this restaurant
	 * @param meal the meal available in this restaurant
	 */
	public void addMeal(Meal meal){
		this.meals.put(meal.getName(), meal);
	}
	/**
	 * Add an admin for this restaurant
	 * @param u the user becoming an admin for this restaurant
	 */
	public void putAdmin(Admin u){
		this.admins.put(u.getUserName(), u);
	}
	/**
	 * Get the admins of this restaurant
	 * @return the admins of this restaurant
	 */
	public HashMap<String,Admin> getAdmins() {
		return admins;
	}
	/**
	 * Set the admins of this restaurant
	 * @param admins the admins of this restaurant
	 */
	public void setAdmins(HashMap<String,Admin> admins) {
		this.admins = admins;
	}
	/**
	 * Add a customer to this restaurant
	 * @param u the customer to add to this restaurant
	 */
	public void putUser(Customer u){
		this.users.put(u.getUserName(), u);
	}
	/**
	 * Get the customers of this restaurant
	 * @return the customers of this restaurant
	 */
	public HashMap<String,Customer> getUsers() {
		return users;
	}
	/**
	 * Set the customers of this restaurant
	 * @param users the customers of this restaurant
	 */
	public void setUsers(HashMap<String,Customer> users) {
		this.users = users;
	}
	/**
	 * Alias for getMeals().get(str)
	 * @param str name a of the meal we want to get
	 * @return the meal having the name str
	 */
	public Meal getMeal(String str){
		return this.meals.get(str);
	}
	
}
