/**
 *
 * Interface every module to contact client (one module for each way we have to contact him) has to implement
 *
 */
public interface ContactSenderInterface {
	/**
	 * 
	 * Sending a message to a contact
	 * @param contact the client we want to contact
	 * @param message the message we want to send
	 */
	public void sendMessage(String contact, String message);
}
