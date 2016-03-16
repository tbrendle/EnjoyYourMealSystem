/**
 * 
 * Factory to instantiate a class to contact a user, depending on how we want to contact him
 * 
 */
public class ContactFactory {
	/**
	 * 
	 * @param s Name of the contacting module we want to create
	 * @return an instance of a module to contact the user
	 */
	public static ContactSenderInterface create(String s){
		if(s=="email"){
			return new ContactEmailer();
		} else if(s=="phone") {
			return new ContactSMS();
		} else 
			return null;
	}
}
