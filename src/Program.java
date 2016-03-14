import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Program implements MealCreationInterface, OrderCreationInterface {
	private User loggedUser;
	private Restaurant restaurant;
	private MealCreator mealCreator;
	private OrderCreator orderCreator;
	private final String name;
	public Program(String name){
		this.name = name;
		this.loadData();
		this.mealCreator = new MealCreator();
	}
	
	public void loadData(){
		    ObjectInputStream ois = null;
		    try {
		      System.out.println("BJRE");
		      final FileInputStream fichier = new FileInputStream(this.name+".ser");
		      ois = new ObjectInputStream(fichier);
		      this.restaurant = (Restaurant) ois.readObject();
		      System.out.println("Restaurant : ");
		      System.out.println("Users : " + this.restaurant.getUsers());
		      System.out.println("Admins : " + this.restaurant.getAdmins());
		      System.out.println("Meals : " + this.restaurant.getMeals());
		    } catch (final java.io.IOException e) {
		    	this.restaurant = new Restaurant();
		    } catch (final ClassNotFoundException e) {
		      e.printStackTrace();
		    } finally {
		      try {
		        if (ois != null) {
		          ois.close();
		        }
		      } catch (final IOException ex) {
		    	ex.printStackTrace();
		      }
		    }
	}
	
	public void saveData(){
	    ObjectOutputStream oos = null;
	    try {
	      final FileOutputStream fichier = new FileOutputStream(this.name+".ser");
	      oos = new ObjectOutputStream(fichier);
	      oos.writeObject(this.restaurant);
	      oos.flush();
	    } catch (final java.io.IOException e) {
	      e.printStackTrace();
	    } finally {
	      try {
	        if (oos != null) {
	          oos.flush();
	          oos.close();
	        }
	      } catch (final IOException ex) {
	        ex.printStackTrace();
	      }
	   }
	}
	
	public HashMap<String, Meal> getAvailableMeals(){
		return this.restaurant.getMeals();
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
		User candidate = restaurant.getAdmins().get(userName);
		if(candidate == null){
			candidate = restaurant.getUsers().get(userName);
		}
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
	
	public HashMap<String, Ingredient> listIngredients(String mealName){
		Meal m = restaurant.getMeals().get(mealName);
		if(m == null)
			throw new IllegalArgumentException("This meal does not exist");
		return m.getIngredients();
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
		Order o = orderCreator.saveOrder();
		restaurant.addOrder(o);
		return o;
	}
	
	public void insertOffer(String mealName, float mealPrice){
		checkAdmin();
		Meal meal = restaurant.getMeal(mealName);
		if(meal==null){
			throw new IllegalStateException("No meal...");
		}
		meal.setSpecialPrice(mealPrice);
		meal.setPromotion(true);
		//This meal will erase itself because it is the same
		restaurant.addMeal(meal);
	}
	public void registerUser(boolean admin, String firstName, String lastName, String userName, String password){
		//Check that user does not exist
		if(restaurant.getAdmins().containsKey(userName))
			throw new IllegalArgumentException("An admin already own this username");
		if(restaurant.getUsers().containsKey(userName))
			throw new IllegalArgumentException("A customer already own this username");
		
		User c = new User(firstName, lastName, userName);
		c.setPassword(password);
		if(admin)
			restaurant.putAdmin(c);
		else
			restaurant.putUser(new Customer(c));
	}
	public void registerClient(String firstName, String lastName, String userName, String password){
		registerUser(false, firstName, lastName, userName, password);
	}
	
	//TODO: Contact type ??
	public void addContactInfo(String contact, String contactType){
		checkCustomer();		
		((Customer) this.loggedUser).putContact(contactType, contact);
		restaurant.putUser((Customer) loggedUser);
	}
	
	public void associateCard(String userName, String cardType){
		//TODO: Check rights.. seems to be admin so...
		checkAdmin();
		Customer customer = restaurant.getUsers().get(userName);
		if(customer==null)
			throw new IllegalArgumentException("User not found...");
		try{
			customer.setFidelityCard(CardFactory.create(cardType));
		} catch (Exception e){
			throw new IllegalArgumentException("Invalid card type");
		}
	}
	
	public void associateAgreement(boolean agreement){
		//TODO: check specs of this function
		checkCustomer();
		((Customer) loggedUser).setSpam(agreement);
	}
	
	public void insertChef(String firstName, String lastName, String userName, String password){
		registerUser(true, firstName, lastName, userName, password);
	}
	//TODO: MAYBE REFACTO TO FACTORY
	public void notify(String message, Iterable<Customer> customerList){
		checkAdmin();
		for(Customer c : customerList){
			if(c.isSpam()){
				ContactSenderInterface csi = ContactFactory.create(c.getPreferedContactType());
				if(csi!=null)
					csi.sendMessage(c.getPreferedContact(), message);
			}
		}
	}
	public void notifyAd(String message, String mealName, float price){
		insertOffer(mealName, price);
		notify(message, restaurant.getUsers().values());
	}
	@SuppressWarnings("deprecation")
	public void notifyBirthday(){
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		Date today = new Date();
		for(Customer c : customerList){
			if(c.getBirthDay().getDate() == today.getDate() && today.getMonth() == c.getBirthDay().getMonth())
				customerList.add(c);
		}
		notify("OMGTHISISYOURBIRTHDAY", customerList);
	}
	
	public ArrayList<Scorable> showMeal(String orderingCriteria){
		SortingOrdersStrategy sorter = SorterFactory.create(orderingCriteria);
		if( sorter == null)
			throw new IllegalArgumentException("Can not sort this way : not yet implemented");
		return sorter.sort(restaurant.getOrdersHistory());
	}
}
