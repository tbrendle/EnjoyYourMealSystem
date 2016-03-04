import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class Customer extends User{
	private boolean spam;
	private String preferedContactType;
	private HashMap<String, String> contacts;
	private HashSet<Meal> favoriteMeals;
	private FidelityStrategy fidelityCard;
	private Date birthDay;
	
	public Customer(String firstName, String lastName, String userName, String password) {
		super(firstName, lastName, userName);
		this.spam = false;
		this.contacts = new HashMap<String, String>();
		this.favoriteMeals = new HashSet<Meal>();
		this.preferedContactType="email";
		this.fidelityCard = new BasicCard();
		//TODO: what is birthday, when will we ask the user ? 
		this.birthDay = new Date();
		this.setPassword(password);
	}
	public Customer(User u){
		super(u.getFirstName(), u.getLastName(), u.getUserName());
		this.hash = u.getHash();
		this.spam = false;
		this.contacts = new HashMap<String, String>();
		this.favoriteMeals = new HashSet<Meal>();
		this.preferedContactType="email";
		this.fidelityCard = new BasicCard();
		this.birthDay = new Date();
	}
	
	public boolean isSpam() {
		return spam;
	}
	public void setSpam(boolean spam) {
		this.spam = spam;
	}
	public HashMap<String, String> getContacts() {
		return contacts;
	}
	public void setContacts(HashMap<String, String> contacts) {
		this.contacts = contacts;
	}
	public void putContact(String key, String value){
		this.contacts.put(key, value);
	}
	public String getPreferedContactType() {
		return preferedContactType;
	}
	public void setPreferedContactType(String preferedContactType) {
		this.preferedContactType = preferedContactType;
	}
	public String getPreferedContact(){
		return this.contacts.get(this.preferedContactType);
	}
	public HashSet<Meal> getFavoriteMeals() {
		return favoriteMeals;
	}
	public void setFavoriteMeals(HashSet<Meal> favoriteMeals) {
		this.favoriteMeals = favoriteMeals;
	}
	public void addFavoriteMeal(Meal meal){
		this.favoriteMeals.add(meal);
	}
	public void removeFavoriteMeal(Meal meal){
		this.favoriteMeals.remove(meal);
	}

	public FidelityStrategy getFidelityCard() {
		return fidelityCard;
	}

	public void setFidelityCard(FidelityStrategy fidelityCard) {
		this.fidelityCard = fidelityCard;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

}
