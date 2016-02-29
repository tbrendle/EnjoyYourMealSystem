public class ContactFactory {
	public static ContactSenderInterface create(String s){
		if(s=="email"){
			return new ContactEmailer();
		} else 
			return null;
	}
}
