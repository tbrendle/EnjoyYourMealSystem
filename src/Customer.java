import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;

/**
 * 
 * One type of User of the system : the restaurants' clients
 * 
 */
public class Customer extends User implements Observer{
	private boolean spam;
	private String preferredContactType;
	private HashMap<String, String> contacts;
	private HashSet<Meal> favoriteMeals;
	private FidelityStrategy fidelityCard;
	private Date birthDay;
	
	/**
	 * Customer constructor from first name, last name, user name and password
	 * @param firstName the first name of the customer
	 * @param lastName the last name of the customer
	 * @param userName the user name of the customer
	 * @param password the password of the customer
	 */
	public Customer(String firstName, String lastName, String userName, String password) {
		super(firstName, lastName, userName, password);
		// By default : we don't spam, we contact the client by email, he owns a basic card.
		this.spam = false;
		this.contacts = new HashMap<String, String>();
		this.favoriteMeals = new HashSet<Meal>();
		this.preferredContactType="email";
		this.fidelityCard = new BasicCard();
		this.birthDay = null;
	}

	
	/**
	 * To know if a customer accepts spam
	 * @return true if the customer accepts spam, false otherwise
	 */
	public boolean isSpam() {
		return spam;
	}
	/**
	 * Change the acceptance of a customer for spam
	 * @param spam boolean (true if the customer accepts spam, false otherwise)
	 */
	public void setSpam(boolean spam) {
		this.spam = spam;
	}
	/**
	 * Get a customer's contacts
	 * @return the contacts of the customer
	 */
	public HashMap<String, String> getContacts() {
		return contacts;
	}
	/**
	 * Set the contacts of a customer
	 * @param contacts the contacts to be set for the customer
	 */
	public void setContacts(HashMap<String, String> contacts) {
		this.contacts = contacts;
	}
	/**
	 * Add a contact to the customer
	 * @param key type of contact (email, phone ...)
	 * @param value value of the contact (email address, phone number...) 
	 */
	public void putContact(String key, String value){
		this.contacts.put(key, value);
	}
	/**
	 * Get preferred contact type
	 * @return the preferred contact type of the customer
	 */
	public String getPreferredContactType() {
		return preferredContactType;
	}
	/**
	 * Set preferred contact type of the customer
	 * @param preferredContactType the preferred contact type
	 */
	public void setPreferredContactType(String preferredContactType) {
		this.preferredContactType = preferredContactType;
	}
	/**
	 * Get preferred contact of the customer
	 * @return the preferred contact of the customer
	 */
	public String getPreferredContact(){
		return this.contacts.get(this.preferredContactType);
	}
	/**
	 * Get favorite meals of a customer
	 * @return favorite meals of a customer
	 */
	public HashSet<Meal> getFavoriteMeals() {
		return favoriteMeals;
	}
	/**
	 * Set favorite meals of a customer
	 * @param favoriteMeals favorite meals of a customer
	 */
	public void setFavoriteMeals(HashSet<Meal> favoriteMeals) {
		this.favoriteMeals = favoriteMeals;
	}
	/**
	 * Add a meal to the list of favorite meals
	 * @param meal the meal to add to favorite meals
	 */
	public void addFavoriteMeal(Meal meal){
		this.favoriteMeals.add(meal);
	}
	/**
	 * Remove a meal from the list of favorite meals
	 * @param meal the meal to remove from the favorites list
	 */
	public void removeFavoriteMeal(Meal meal){
		this.favoriteMeals.remove(meal);
	}
	/**
	 * Get the fidelity card of the customer
	 * @return the fidelity card of the customer
	 */
	public FidelityStrategy getFidelityCard() {
		return fidelityCard;
	}
	/**
	 * Get the fidelity card of the user
	 * @param fidelityCard the fidelity card to associate to the customer
	 */
	public void setFidelityCard(FidelityStrategy fidelityCard) {
		this.fidelityCard = fidelityCard;
	}

	/**
	 * Get the birthday of a user
	 * @return the birthday of a user
	 */
	public Date getBirthDay() {
		return birthDay;
	}

	/**
	 * Set the birthday of a user
	 * @param birthDay the birthday of the user
	 */
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	/**
	 * Send a message to a user using his preferredContactType
	 * @param message the message to be sent
	 */
	public void sendMessage(String message){
		ContactSenderInterface csi = ContactFactory.create(getPreferredContactType());
		if(csi!=null)
			csi.sendMessage(getPreferredContact(), message);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof GeneralNotifier && arg instanceof String){
			sendMessage((String)arg);
		} else if(o instanceof DateChangedNotifier){
			Date today = new Date();
			if(getBirthDay().getDate() == today.getDate() && today.getMonth() == getBirthDay().getMonth())
				sendMessage("THIS IS YOUR BIRTHDAY");
		}
	}

}
