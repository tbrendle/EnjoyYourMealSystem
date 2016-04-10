/**
 * 
 */
package clui;

import core.Customer;
/**
 * Displaying view when informations are asked about a customer
 */
public class CustomerDataView extends AbstractView {
	/**
	 * Show informations about a customer
	 * @param result the user we ask informations about
	 */
	@Override
	public void show(Object result) {
		if(result instanceof Customer){
			Customer c = (Customer) result;
			System.out.println("Name : "+c.getFullName());
			System.out.println("Username : "+c.getUserName());
			System.out.println("Birthday : "+c.getBirthDay());
			System.out.println("Card : "+c.getFidelityCard().getName());
			System.out.println("accept advertizing : "+ (c.isSpam() ? "Y" : "N"));
			System.out.println("prefered way of communication : "+c.getPreferredContactType());
			for(String key : c.getContacts().keySet())
				System.out.println(key+" : "+c.getContacts().get(key));
		}
		
		
	}
	
}
