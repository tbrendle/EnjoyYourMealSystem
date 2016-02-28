
public class Program implements MealCreationInterface, OrderCreationInterface {
	private User loggedUser;
	private Restaurant restaurant;
	private MealCreator mealCreator;
	private OrderCreator orderCreator;
	
	public Program(){
		this.loadData();
		this.mealCreator = new MealCreator();
	}
	
	public void loadData(){
		//TODO: Load Restaurant data from some db/file/...
		this.restaurant = new Restaurant();
	}
	
	public void saveData(){
		//TODO: save Restaurant data from some db/file/...
	}
	
	public void checkCustomer(){
		if(!(loggedUser instanceof Customer))
			throw new SecurityException("Operation not allowed : must be authentified customer");
	}
	
	public void checkAdmin(){
		if((loggedUser instanceof Customer) || loggedUser == null)
			throw new SecurityException("Operation not allowed : must be authentified administrator");
	}
	
	public void authenticateUser(String userName, String password){
		User candidate = restaurant.getUsers().get(userName);
		if(candidate == null){
			throw new SecurityException("User not found");
		}
		if(!candidate.authenticate(password)){
			throw new SecurityException("Incorrect password");
		}
		this.loggedUser = candidate;
		if(this.loggedUser instanceof Customer){
			this.orderCreator = new OrderCreator(restaurant, (Customer) loggedUser);
		}
	}
	
	public void logout(){
		orderCreator = null;
		loggedUser = null;
	}
	
	@Override
	public void createMeal(String mealName, float price) {
		checkAdmin();
		this.mealCreator.createMeal(mealName, price);
	}

	@Override
	public void addIngredient(String ingredientName, Integer quantity) {
		checkAdmin();
		this.mealCreator.addIngredient(ingredientName, quantity);
	}

	@Override
	public Meal currentMeal() {
		checkAdmin();
		return this.mealCreator.currentMeal();
	}

	@Override
	public Meal saveMeal() {
		checkAdmin();
		Meal m = mealCreator.saveMeal();
		this.restaurant.addMeal(m);
		return m;
	}

	@Override
	public void selectMeal(String mealName, Integer quantity) {
		checkCustomer();
		orderCreator.selectMeal(mealName, quantity);
	}

	@Override
	public void personalizeMeal(String mealName, String ingredientName, Integer quantity) {
		checkCustomer();
		orderCreator.personalizeMeal(mealName, ingredientName, quantity);
	}

	@Override
	public Order currentOrder() {
		checkCustomer();
		return orderCreator.currentOrder();
	}

	@Override
	public float evalPrice() {
		checkCustomer();
		return orderCreator.evalPrice();
	}

	@Override
	public Order saveOrder() {
		checkCustomer();
		return orderCreator.saveOrder();
	}
	
	public static void main(String[] args) {
		Program p = new Program();
	}
}
