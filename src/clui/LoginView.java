package clui;

import core.Admin;
import core.Customer;
import core.User;

public class LoginView extends AbstractView {
	
	@Override
	public void show(Object user) {
		if(user instanceof Admin){
			System.out.println(((User) user).getUserName()+ " successfully logged in as admin");
			System.out.println("You can type : ");
			System.out.println("addMeal<MealName, price>: to create a meal");
			System.out.println("insertOffer<mealName, price> : to insert offer");
			System.out.println("showMeals<> to see the available meals");
			System.out.println("showMeals<Sorting Strategy> to see stats about meal");
			System.out.println("associateCard <username, cardType> to associate a fidelity card to an user account");
			System.out.println("associateAgreement <username, (y/n)> to set a user contact agreement");
			System.out.println("addContactInfo<username, contactInfo, contactType> to add a contact info type to your account"); 
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
