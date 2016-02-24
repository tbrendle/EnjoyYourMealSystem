import java.util.HashMap;

public class User {
	private final String firstName;
	private final String lastName;
	private final String userName;
	private String hash;
	private boolean spam;
	private String preferedContactType;
	private HashMap<String, String> contacts;
	
	public User(String firstName, String lastName, String userName){
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.contacts = new HashMap<String, String>();
		this.spam = false;
		this.preferedContactType="email";
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getFullName() {
		return firstName+" "+lastName;
	}
	public String getUserName() {
		return userName;
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
	public boolean authenticate(String password){
		return SecurityController.hashPassword(password).equals(this.hash);
	}
	public void setPassword(String password) {
		this.hash = SecurityController.hashPassword(password);
	}
	public String getHash(){
		return this.hash;
	}
	
}
