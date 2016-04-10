package clui;

/**
 * Displaying view when the user starts the program
 */
public class LandingView extends AbstractView{
	/**
	 * Show available commands when a user starts the program : add a user or login
	 * @param result not used here
	 */
	public void show(Object result){
		System.out.println("Welcome to the EYMS software landing page");
		System.out.println("Here you can type :");
		System.out.println("login <username, password>  : to login in");
		System.out.println("registerClient <firstName, lastName, username, password> : to add auser to the system");
		System.out.println("insertChef <firstName, lastName, username, password> : to add a chef to the system");
	}
}
