package core;
/**
 * 
 * Class to be instantiated to contact a client via sms
 *
 */
public class ContactSMS implements ContactSenderInterface{
	/**
	 * Sends a message to a user, using sms
	 * @param contact the name of the contact
	 * @param message the message we want to send
	 */
	@Override
	public void sendMessage(String contact, String message) {
		System.out.println("SENDING MESSAGE:"+message+":TO:"+contact+":BY:SMS");
	}
}
