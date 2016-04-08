package clui;

import core.Admin;
import core.Customer;
import core.User;

public class LoginView extends AbstractView {
	
	@Override
	public void show(Object user) {
		if(user instanceof Admin){
			System.out.println(((User) user).getUserName()+ " successfully logged in as admin");
		} else if (user instanceof Customer) {
			System.out.println("Hello, "+ ((User) user).getFullName());
			System.out.println("You can type : ");
			System.out.println("showMeals<> to see the available meals");
			System.out.println("selectMeal <mealName, quantity> to add a meal to your order");
			System.out.println("associateCard <cardType> to associate a fidelity card to your account");
			System.out.println("associateAgreement <(y/n)> to set your contact agreement");
			System.out.println("addContactInfo<contactInfo, contactType> to add a contact info type to your account"); 
		} else {
			System.out.println("No user logged in");
		}
	}

}
