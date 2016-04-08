package clui;

public class LandingView extends AbstractView{

	public void show(Object o){
		System.out.println("Welcome to the EYMS software landing page");
		System.out.println("Here you can type :");
		System.out.println("login <username, password>  : to login in");
		System.out.println("registerClient <firstName, lastName, username, password> : to add auser to the system");
		System.out.println("insertChef <firstName, lastName, username, password> : to add a chef to the system");
	}
}