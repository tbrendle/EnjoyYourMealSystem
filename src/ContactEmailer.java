
public class ContactEmailer implements ContactSenderInterface{
	@Override
	public void sendMessage(String contact, String message) {
		System.out.println("SENDING MESSAGE:"+message+":TO:"+contact+":BY:email");
	}
}
