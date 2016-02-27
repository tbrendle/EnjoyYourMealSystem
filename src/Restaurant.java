import java.util.ArrayList;
import java.util.HashSet;

public class Restaurant {
	private HashSet<Customer> users;
	private HashSet<User> admins;
	private HashSet<Meal> meals;
	private ArrayList<Order> ordersHistory;
	public Restaurant(){
		this.users = new HashSet<Customer>();
		this.admins = new HashSet<User>();
		this.meals = new HashSet<Meal>();
	}
	public HashSet<User> getAdmins() {
		return admins;
	}
	public void setAdmins(HashSet<User> admins) {
		this.admins = admins;
	}
	public void addAdmin(User user){
		this.admins.add(user);
	}
	public void removeAdmin(User user){
		this.admins.remove(user);
	}
	public HashSet<Meal> getMeals() {
		return meals;
	}
	public void setMeals(HashSet<Meal> meals) {
		this.meals = meals;
	}
	public Meal getExistingMealByString(String s){
		for(Meal m : meals){
			if(m.equals(s)){
				return m;
			}
		}
		return null;
	}
	public HashSet<Customer> getUsers() {
		return users;
	}
	public void setUsers(HashSet<Customer> users) {
		this.users = users;
	}
	public void addUser(Customer user){
		this.users.add(user);
	}
	public void removeUser(User user){
		this.users.remove(user);
	}
	public ArrayList<Order> getOrdersHistory() {
		return ordersHistory;
	}
	public void setOrdersHistory(ArrayList<Order> ordersHistory) {
		this.ordersHistory = ordersHistory;
	}
	public void addOrderHistory(Order o){
		this.ordersHistory.add(o);
	}
	
}
