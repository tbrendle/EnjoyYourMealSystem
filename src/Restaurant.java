import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Restaurant implements Serializable {
	private HashMap<String,Customer> users;
	private HashMap<String,User> admins;
	private HashMap<String,Meal> meals;
	private ArrayList<Order> ordersHistory;
	public Restaurant(){
		this.setUsers(new HashMap<String,Customer>());
		this.setAdmins(new HashMap<String,User>());
		this.setMeals(new HashMap<String,Meal>());
		this.setOrdersHistory(new ArrayList<Order>());
	}
	public ArrayList<Order> getOrdersHistory() {
		return ordersHistory;
	}
	public void setOrdersHistory(ArrayList<Order> ordersHistory) {
		this.ordersHistory = ordersHistory;
	}
	public void addOrder(Order o){
		this.ordersHistory.add(o);
	}
	public HashMap<String,Meal> getMeals() {
		return meals;
	}
	public void setMeals(HashMap<String,Meal> meals) {
		this.meals = meals;
	}
	public void addMeal(Meal meal){
		this.meals.put(meal.getName(), meal);
	}
	public void putAdmin(User u){
		this.admins.put(u.getUserName(), u);
	}
	public HashMap<String,User> getAdmins() {
		return admins;
	}
	public void setAdmins(HashMap<String,User> admins) {
		this.admins = admins;
	}
	public void putUser(Customer u){
		this.users.put(u.getUserName(), u);
	}
	public HashMap<String,Customer> getUsers() {
		return users;
	}
	public void setUsers(HashMap<String,Customer> users) {
		this.users = users;
	}
	// Alias for getMeals().get(str)
	public Meal getMeal(String str){
		return this.meals.get(str);
	}
	
}
