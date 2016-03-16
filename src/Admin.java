/**
 * 
 * One type of User of the system : the restaurants' managers
 *
 */
public class Admin extends User{

	/**
	 * @param firstName
	 * @param lastName
	 * @param userName
	 */
	public Admin(String firstName, String lastName, String userName, String password) {
		super(firstName, lastName, userName, password);
	}


}
