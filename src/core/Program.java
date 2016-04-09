package core;
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
	private final String inputName;
	private final String outputName;
	
	/**
	 * Program constructor
	 * @param name name of the program
	 */
	public Program(String name){
		this.inputName = name;
		this.outputName = name;
		initProgram();
	}
	
	/**
	 * Program constructor for tests purpose
	 * @param inputName name of the input file
	 * @param outputName name of the output file
	 */
	public Program(String inputName, String outputName){
		this.inputName = inputName;
		this.outputName = outputName;
		initProgram();
	}
	
	/**
	 * Initialize the program
	 * This method must be called only by the constructor
	 */
	private void initProgram(){
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
	 * Loading serialized data to this instance of the program
	 */
	public void loadData(){
		    ObjectInputStream ois = null;
		    try {
		      final FileInputStream fichier = new FileInputStream(this.inputName+".ser");
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
	      final FileOutputStream fichier = new FileOutputStream(this.outputName+".ser");
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
	 * Check if the current user of the system is a customer
	 * @throws SecurityException if nobody is logged in or if the current logged in user is not a customer
	 */
	public void checkCustomer(){
		if(!(loggedUser instanceof Customer))
			throw new SecurityException("Operation not allowed : must be authentified customer");
	}
	
	/**
	 * Check if the current user of the system is an admin
	 * @throws SecurityException  if nobody is logged in or if the current logged in user is not a admin
	 */
	public void checkAdmin(){
		if(!(loggedUser instanceof Admin))
			throw new SecurityException("Operation not allowed : must be authentified administrator");
	}
	
	/**
	 * Authenticate a user being given its name and password
	 * @param userName the name of the user we try to authenticate
	 * @param password the password of the user we try to authenticate
	 * @throws SecurityException if used not found or if the password is incorrect
	 */
	public User login(String userName, String password){
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
		return loggedUser;
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
	 * @throws IllegalArgumentException if the meal was not found
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
	public Meal createMeal(String mealName, float price) {
		checkAdmin();
		this.mealCreator.createMeal(mealName, price);
		return currentMeal();
	}

	/**
	 * Add an ingredient to the meal currently being created
	 * @param ingredientName the name of the ingredient we're adding
	 * @param quantity the quantity of the ingredien we're adding
	 */
	public Meal addIngredient(String ingredientName, Integer quantity) {
		checkAdmin();
		this.mealCreator.addIngredient(ingredientName, quantity);
		return currentMeal();
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
	public Meal selectMeal(String mealName, Integer quantity) {
		checkCustomer();
		orderCreator.selectMeal(mealName, quantity);
		return orderCreator.getCurrentMeal();
	}
	
	/**
	 * Personalize the currentMeal, that is to say an ingredient of this meal
	 * @param ingredientName the name of the ingredient from the meal we want to personalize
	 * @param quantity the quantity we want to personalize it from
	 */
	public Meal personalizeMeal(String ingredientName, Integer quantity) {
		checkCustomer();
		orderCreator.personalizeMeal(ingredientName, quantity);
		return orderCreator.getCurrentMeal();
	}
	
	/**
	 * Personalize a meal given its index, that is to say an ingredient of this meal
	 * @param ingredientName the name of the ingredient from the meal we want to personalize
	 * @param quantity the quantity we want to personalize it from
	 * @param index the id of the meal in the order array
	 */
	public void personalizeMeal(String ingredientName, Integer quantity, Integer index) {
		checkCustomer();
		orderCreator.personalizeMeal(ingredientName, quantity, index);
	}

	/**
	 * Getting the current state of the order being made
	 * @return the current state of the order being made
	 */
	public OrderCreator currentOrder() {
		checkCustomer();
		return orderCreator;
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
	 * @throws IllegalStateException if the meal was not found
	 */
	public Meal insertOffer(String mealName, float mealPrice){
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
		return meal;
	}
	/**
	 * Add a user to the restaurant
	 * @param admin boolean to specify if the user is an admin or not
	 * @param firstName the first name of the user
	 * @param lastName the last name of the user
	 * @param userName the user name of the user
	 * @param password the password of the user
	 * @throws IllegalArgumentException if the username is already taken
	 */
	public void registerUser(boolean admin, String firstName, String lastName, String userName, String password){
		//Check that user does not exist
		if(restaurant.getAdmins().containsKey(userName))
			throw new IllegalArgumentException("An admin already own this username");
		if(restaurant.getUsers().containsKey(userName))
			throw new IllegalArgumentException("A customer already own this username");
		if(admin)
			restaurant.putAdmin(new Admin(firstName, lastName, userName, password));
		else
			restaurant.putUser(new Customer(firstName, lastName, userName, password));
		saveData();
	}
	/**
	 * Add a customer to the restaurant
	 * @param firstName the first name of the customer
	 * @param lastName the last name of the customer
	 * @param userName the user name of the customer
	 * @param password the password of the customer
	 */
	public void registerClient(String firstName, String lastName, String userName, String password){
		registerUser(false, firstName, lastName, userName, password);
	}
	
	/**
	 * Add a contact info to a customer
	 * @param contact the contact to be added
	 * @param contactType the type of the contact to be added
	 */
	public Customer addContactInfo(String contact, String contactType){
		checkCustomer();		
		((Customer) this.loggedUser).putContact(contactType, contact);
		saveData();
		return ((Customer) this.loggedUser);
	}
	
	/**
	 * Add a contact info to a customer
	 * @param userName the username of the User we want to add a contact to
	 * @param contact the contact to be added
	 * @param contactType the type of the contact to be added
	 */
	public Customer addContactInfo(String userName, String contact, String contactType){
		checkAdmin();		
		Customer customer = restaurant.getUsers().get(userName);
		if(customer==null)
			throw new IllegalArgumentException("User not found...");
		customer.putContact(contactType, contact);
		saveData();
		return customer;
	}
	
	/**
	 * Associate a card to the current user
	 * @param cardType the card type to associate to the current user
	 * @throws IllegalArgumentException if the card type is invalid
	 */
	public Customer associateCard(String cardType){
		checkCustomer();
		try {
			((Customer) loggedUser).setFidelityCard(CardFactory.create(cardType));
		} catch (Exception e){
			throw new IllegalArgumentException("Invalid card type");
		}
		saveData();
		return (Customer) loggedUser;
	}
	
	/**
	 * Association a card to a given user
	 * @param userName the name of the user to associated the card to
	 * @param cardType the card type to associate to the current user
	 * @throws IllegalArgumentException if the user was not found or the card type was invalid
	 */
	public Customer associateCard(String userName, String cardType){
		checkAdmin();
		Customer customer = restaurant.getUsers().get(userName);
		if(customer==null)
			throw new IllegalArgumentException("User not found...");
		try{
			customer.setFidelityCard(CardFactory.create(cardType));
		} catch (Exception e){
			throw new IllegalArgumentException("Invalid card type");
		}
		saveData();
		return customer;
	}
	
	
	/**
	 * Associate an agreement (for spam) to an user
	 * @param customer the customer 
	 * @param agreement true if the current user accepts spam, false otherwise
	 */
	public Customer associateAgreement(Customer customer, boolean agreement){
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
		return customer;
	}
	
	/**
	 * Associate an agreement (for spam) to the current user
	 * @param agreement true if the current user accepts spam, false otherwise
	 */
	public Customer associateAgreement(boolean agreement){
		checkCustomer();
		return associateAgreement((Customer) loggedUser, agreement);
	}
	
	/**
	 * Associate an agreement (for spam) to a given user
	 * @param userName the name of the user to associated the agreemnt to
	 * @param agreement true if the current user accepts spam, false otherwise
	 */
	public Customer associateAgreement(String userName, boolean agreement){
		checkAdmin();
		Customer customer = restaurant.getUsers().get(userName);
		if(customer==null)
			throw new IllegalArgumentException("User not found...");
		return associateAgreement(customer, agreement);
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
	 * To see the list of the meals available in the restaurant
	 * @return the list of meals
	 */
	public HashMap<String, Meal> showMeal(){
		return restaurant.getMeals();
	}
	
	/**
	 * To see the list of the meals ordered according to a certain criteria
	 * @param orderingCriteria the criteria with which we order meals
	 * @return the ordered list of meals
	 * @throws IllegalArgumentException if the sorting criteria is not implemented yet
	 */
	public ArrayList<ScorableMeal> showMeal(String orderingCriteria){
		try {
			SortingOrdersStrategy sorter = SorterFactory.create(orderingCriteria);
			return sorter.sort(restaurant.getOrdersHistory(), restaurant.getMeals());
		}
		catch (IllegalArgumentException e) {
			throw e;
		}
	}
	
}
