import java.util.HashMap;
import java.util.HashSet;

public class Customer extends User{
	private boolean spam;
	private String preferedContactType;
	private HashMap<String, String> contacts;
	private HashSet<Meal> favoriteMeals;
	private FidelityStrategy fidelityCard;
	
	public Customer(String firstName, String lastName, String userName) {
		super(firstName, lastName, userName);
		this.spam = false;
		this.contacts = new HashMap<String, String>();
		this.favoriteMeals = new HashSet<Meal>();
		this.preferedContactType="email";
		this.fidelityCard = new BasicCard();
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

}