import java.io.Serializable;

/**
 * Class to manage Users in general (customers and admins)
 */
public abstract class User implements Serializable{
	private final String firstName;
	private final String lastName;
	private final String userName;
	protected String hash;
	
	/**
	 * User constructor
	 * @param firstName the first name of the user
	 * @param lastName the last name of the user
	 * @param userName the user name of the user
	 * @param password the unencrypted password of the user
	 */
	public User(String firstName, String lastName, String userName, String password){
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.setPassword(password);
	}
	
	
	/**
	 * @return the hashcode associated to this user
	 */
	@Override
	public int hashCode(){
		return this.userName.hashCode();
	}
	
	/**
	 * Test if a given object (User) is the same as this user
	 * @param user an object (user)
	 * @return true if the given object is the same as this user, false otherwise
	 */
	@Override
	public boolean equals(Object user){
		if(user instanceof User)
			return this.userName.equals(((User) user).getUserName());
		return false;
	}
	
	/**
	 * Get the first name of the user
	 * @return the first name of the user
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * Get the last name of the user
	 * @return the first name of the user
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * Get the full name of the user
	 * @return the full name of the user
	 */
	public String getFullName() {
		return firstName+" "+lastName;
	}
	/**
	 * Get the user name of the user
	 * @return the user name of the user
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * Authenticate a user
	 * @param password the password entered by the human
	 * @return true if the password corresponds to this user, false otherwise
	 */
	public boolean authenticate(String password){
		return PasswordHandler.hashPassword(password).equals(this.hash);
	}
	/**
	 * Set the password of the user. Actually only stores the hash of this password for security concerns.
	 * @param password the password to be set
	 */
	public void setPassword(String password) {
		this.hash = PasswordHandler.hashPassword(password);
	}
	/**
	 * Get the hash associated to the user's password
	 * @return the hash associated to the user's password
	 */
	public String getHash(){
		return this.hash;
	}
}
