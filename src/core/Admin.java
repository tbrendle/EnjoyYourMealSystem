package core;
/**
 * 
 * One type of User of the system : the restaurants' managers
 *
 */
public class Admin extends User{

	/**
	 * @param firstName the first name of the admin
	 * @param lastName the last name of the admin
	 * @param userName the user name of the admin
	 * @param password the password of the admin
	 */
	public Admin(String firstName, String lastName, String userName, String password) {
		super(firstName, lastName, userName, password);
	}


}
