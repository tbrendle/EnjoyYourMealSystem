import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class to handle general operations required by the EYMS system
 */
public class Program {
	private User loggedUser;
	private Restaurant restaurant;
	private MealCreator mealCreator;
	private GeneralNotifier generalNotifier;
	private DateChangedNotifier dateChangedNotifier;
	private OrderCreator orderCreator;
	private final String name;
	/**
	 * Program constructor
	 * @param name name of the program
	 */
	public Program(String name){
		this.name = name;
		// We load the serialized data
		this.loadData();
		this.generalNotifier = new GeneralNotifier();
		this.dateChangedNotifier = new DateChangedNotifier();
		//Load notifiers
		for(Customer c : this.restaurant.getUsers().values()){
			if(c.isSpam()){
				this.generalNotifier.addObserver(c);
				this.dateChangedNotifier.addObserver(c);
			}
		}
		//Run date watcher
		(new Thread(dateChangedNotifier)).start();
		
		this.mealCreator = new MealCreator();
	}
	
	/**
	 * Loading seralized data to this instance of the program
	 */
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
	
	/**
	 * Serialize data associated to this instance of the program and store them on the computer 
	 */
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
	
	/**
	 * Get the available meals for this instance of the program
	 * @return the available meals for this instance of the program
	 */
	public HashMap<String, Meal> getAvailableMeals(){
		return this.restaurant.getMeals();
	}
	
	/**
	 * Check if the current user of the system is a customer
	 * @throws SecurityException
	 */
	public void checkCustomer(){
		if(!(loggedUser instanceof Customer))
			throw new SecurityException("Operation not allowed : must be authentified customer");
	}
	
	/**
	 * Check if the current user of the system is an admin
	 * @throws SecurityException
	 */
	public void checkAdmin(){
		if((loggedUser instanceof Customer) || loggedUser == null)
			throw new SecurityException("Operation not allowed : must be authentified administrator");
	}
	
	/**
	 * Authenticate a user being given its name and password
	 * @param userName the name of the user we try to authenticate
	 * @param password the password of the user we try to authenticate
	 * @throws SecurityException
	 */
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
	
	/**
	 * Log out user
	 */
	public void logout(){
		orderCreator = null;
		loggedUser = null;
	}
	
	/**
	 * List ingredients for a given meal name
	 * @param mealName the name of the meal we want ingredients from
	 * @return an hashmap of the ingredients
	 * @throws IllegalArgumentException
	 */
	public HashMap<String, Ingredient> listIngredients(String mealName){
		Meal m = restaurant.getMeals().get(mealName);
		if(m == null)
			throw new IllegalArgumentException("This meal does not exist");
		return m.getIngredients();
	}
	
	/**
	 * Create a meal given a name and a price
	 * @param mealName the name of the meal we want to create 
	 * @param price the price associated to the meal we're creating
	 */
	public void createMeal(String mealName, float price) {
		checkAdmin();
		this.mealCreator.createMeal(mealName, price);
	}

	/**
	 * Add an ingredient to the meal currently being created
	 * @param ingredientName the name of the ingredient we're adding
	 * @param quantity the quantity of the ingredien we're adding
	 */
	public void addIngredient(String ingredientName, Integer quantity) {
		checkAdmin();
		this.mealCreator.addIngredient(ingredientName, quantity);
	}

	/**
	 * Get the current state of the meal being created
	 * @return the current state of the meal being created
	 */
	public Meal currentMeal() {
		checkAdmin();
		return this.mealCreator.currentMeal();
	}

	/**
	 * Save the meal currently being created in the restaurant
	 * @return the meal newly created in the restaurant
	 */
	public Meal saveMeal() {
		checkAdmin();
		Meal m = mealCreator.saveMeal();
		this.restaurant.addMeal(m);
		saveData();
		return m;
	}

	/**
	 * Select a meal from its name and associate a quantity to this meal selected
	 * @param mealName the name of the meal to select
	 * @param quantity the quantity of the meal to select
	 */
	public void selectMeal(String mealName, Integer quantity) {
		checkCustomer();
		orderCreator.selectMeal(mealName, quantity);
	}
	
	/**
	 * Personalize a meal, that is to say an ingredient of this meal
	 * @param mealName the name of the meal we're personalizing
	 * @param ingredientName the name of the ingredient from the meal we want to personalize
	 * @param quantity the quantity we want to personalize it from
	 */
	public void personalizeMeal(String mealName, String ingredientName, Integer quantity) {
		checkCustomer();
		orderCreator.personalizeMeal(mealName, ingredientName, quantity);
	}

	/**
	 * Getting the current state of the order being made
	 * @return the current state of the order being made
	 */
	public Order currentOrder() {
		checkCustomer();
		return orderCreator.currentOrder();
	}

	/**
	 * Evaluation the price of the current order
	 * @return the price of the current order
	 */
	public float evalPrice() {
		checkCustomer();
		return orderCreator.evalPrice();
	}
	
	/**
	 * Save the order currently being made
	 * @return the order saved
	 */
	public Order saveOrder() {
		checkCustomer();
		Order o = orderCreator.saveOrder();
		restaurant.addOrder(o);
		saveData();
		return o;
	}
	
	/**
	 * Add on offer to a given meal
	 * @param mealName the name of the meal we want to associated an offer to
	 * @param mealPrice the price of the meal under promotion
	 * @throws IllegalStateException
	 */
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
		saveData();
	}
	/**
	 * Add a user to the restaurant
	 * @param admin boolean to specify if the user is an admin or not
	 * @param firstName the first name of the user
	 * @param lastName the last name of the user
	 * @param userName the user name of the user
	 * @param password the password of the user
	 * @throws IllegalArgumentException
	 */
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
		saveData();
	}
	/**
	 * Add a customer to the restaurant
	 * @param firstName the first name of the customer
	 * @param lastName the last name of the customer
	 * @param userName the user name of the customer
	 * @param password the password of the customer
	 */
	public void registerCustomer(String firstName, String lastName, String userName, String password){
		registerUser(false, firstName, lastName, userName, password);
	}
	
	//TODO: Contact type ??
	/**
	 * Add a contact info to a customer
	 * @param contact the contact to be added
	 * @param contactType the type of the contact to be added
	 */
	public void addContactInfo(String contact, String contactType){
		checkCustomer();		
		((Customer) this.loggedUser).putContact(contactType, contact);
		restaurant.putUser((Customer) loggedUser);
	}
	
	/**
	 * Associate a card to the current user
	 * @param cardType the card type to associate to the current user
	 * @throws IllegalArgumentException
	 */
	public void associateCard(String cardType){
		checkCustomer();
		try {
			((Customer) loggedUser).setFidelityCard(CardFactory.create(cardType));
			restaurant.putUser((Customer) loggedUser);
		} catch (Exception e){
			throw new IllegalArgumentException("Invalid card type");
		}
	}
	
	/**
	 * Association a card to a given user
	 * @param userName the name of the user to associated the card to
	 * @param cardType the card type to associate to the current user
	 * @throws IllegalArgumentException
	 */
	public void associateCard(String userName, String cardType){
		checkAdmin();
		Customer customer = restaurant.getUsers().get(userName);
		if(customer==null)
			throw new IllegalArgumentException("User not found...");
		try{
			customer.setFidelityCard(CardFactory.create(cardType));
			restaurant.putUser(customer);
		} catch (Exception e){
			throw new IllegalArgumentException("Invalid card type");
		}
	}
	
	
	/**
	 * Associate an agreement (for spam) to an user
	 * @param agreement true if the current user accepts spam, false otherwise
	 * @param user the user 
	 */
	public void associateAgreement(Customer customer, boolean agreement){
		//Update notifiers
		if(customer.isSpam() && !agreement){
			generalNotifier.deleteObserver(customer);
			dateChangedNotifier.deleteObserver(customer);
		} else if(!customer.isSpam() && agreement){
			generalNotifier.addObserver(customer);
			dateChangedNotifier.addObserver(customer);
		}
		//Save preferences of the user
		customer.setSpam(agreement);
		restaurant.putUser(customer);
		saveData();
	}
	
	/**
	 * Associate an agreement (for spam) to the current user
	 * @param agreement true if the current user accepts spam, false otherwise
	 */
	public void associateAgreement(boolean agreement){
		checkCustomer();
		associateAgreement((Customer) loggedUser, agreement);
	}
	
	/**
	 * Associate an agreement (for spam) to a given user
	 * @param userName the name of the user to associated the agreemnt to
	 * @param agreement true if the current user accepts spam, false otherwise
	 */
	public void associateAgreement(String userName, boolean agreement){
		checkAdmin();
		Customer customer = restaurant.getUsers().get(userName);
		if(customer==null)
			throw new IllegalArgumentException("User not found...");
		associateAgreement(customer, agreement);
	}
	
	/**
	 * Add a chef to the restaurant of this instance of the program
	 * @param firstName first name of the chef
	 * @param lastName last name of the chef
	 * @param userName user name of the chef
	 * @param password password of the chef
	 */
	public void insertChef(String firstName, String lastName, String userName, String password){
		registerUser(true, firstName, lastName, userName, password);
	}

	/**
	 * Send a message to alert the users about an offer
	 * @param message the message to send to users
	 * @param mealName the name of the meal having a promotion on
	 * @param price the special price of this meal
	 */
	public void notifyAd(String message, String mealName, float price){
		insertOffer(mealName, price);
		generalNotifier.notifyAll(message);
	}

	/**
	 * Send a special offer to the users that celebrate their birthday
	 */
	public void notifyBirthday(){
		dateChangedNotifier.notifyObservers();
	}
	
	/**
	 * To see the list of the meals ordered according to a certain criteria
	 * @param orderingCriteria the criteria with which we order meals
	 * @return the ordered list of meals
	 * @throws IllegalArgumentException
	 */
	public ArrayList<ScorableMeal> showMeal(String orderingCriteria){
		SortingOrdersStrategy sorter = SorterFactory.create(orderingCriteria);
		if( sorter == null)
			throw new IllegalArgumentException("Can not sort this way : not yet implemented");
		return sorter.sort(restaurant.getOrdersHistory());
	}
}
