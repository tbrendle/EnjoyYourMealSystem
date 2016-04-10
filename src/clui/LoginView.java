package clui;

import core.Admin;
import core.Customer;
import core.User;

/**
 * Displaying a view when a user has logged in
 */
public class LoginView extends AbstractView {
	/**
	 * Display the available commands for the logged in user, depending on its rights
	 * @param user the user logged in
	 */
	@Override
	public void show(Object user) {
		if(user instanceof Admin){
			System.out.println(((User) user).getUserName()+ " successfully logged in as admin");
			System.out.println("You can type : ");
			System.out.println("createMeal<MealName, price>: to create a meal");
			System.out.println("insertOffer<mealName, price> : to insert offer");
			System.out.println("removeFromSpecialOffer<mealName, price> : to insert offer");
			System.out.println("showMeal<> to see the available meals");
			System.out.println("showMeal<Sorting Strategy> to see stats about meal");
			System.out.println("listIngredients<mealName> to show all the ingredients of a meal");
			System.out.println("setBirthday <username, date> to associate a birthday to an user account");
			System.out.println("associateCard <username, cardType> to associate a fidelity card to an user account");
			System.out.println("associateAgreement <username, (y/n)> to set a user contact agreement");
			System.out.println("addContactInfo<username, contactInfo, contactType> to add a contact info type to your account"); 
			System.out.println("notifyAd<message, mealName, price> : to set a promotion and notify the users");
			System.out.println("notifyBirthday<> : To notify all users that it is their birthday");
		} else if (user instanceof Customer) {
			System.out.println("Hello, "+ ((User) user).getFullName());
			System.out.println("You can type : ");
			System.out.println("showMeal<> to see the available meals");
			System.out.println("listIngredients<mealName> to show all the ingredients of a meal");
			System.out.println("selectMeal <mealName, quantity> to add a meal to your order");
			System.out.println("associateCard <cardType> to associate a fidelity card to your account");
			System.out.println("setBirthday <date> to associate a birthday to your account");
			System.out.println("associateAgreement <(y/n)> to set your contact agreement");
			System.out.println("addContactInfo<contactInfo, contactType> to add a contact info type to your account"); 
		} else {
			//Only happens at logout
			System.out.println("User successfully logged out.");
			System.out.println("You can now log in again or create a new user :");
			System.out.println("login <username, password>  : to login in");
			System.out.println("registerClient <firstName, lastName, username, password> : to add auser to the system");
			System.out.println("insertChef <firstName, lastName, username, password> : to add a chef to the system");
		}
	}

}
