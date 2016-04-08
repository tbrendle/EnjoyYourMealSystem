/**
 * 
 */
package clui;

import core.Customer;

public class CustomerDataView extends AbstractView {

	@Override
	public void show(Object result) {
		if(result instanceof Customer){
			Customer c = (Customer) result;
			System.out.println("Name : "+c.getFullName());
			System.out.println("Username : "+c.getUserName());
			System.out.println("Your preferences are : ");
			System.out.println("accept advertizing : "+ (c.isSpam() ? "Y" : "N"));
			System.out.println("prefered way of communication : "+c.getPreferredContactType());
			for(String key : c.getContacts().keySet())
				System.out.println(key+" : "+c.getContacts().get(key));
		}
		
		
	}
	
}
