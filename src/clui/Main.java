package clui;
import java.util.Scanner;

import core.Program;

/**
 * Main class for CLUI, containing main method
 */
public class Main {
	/**
	 * main method for CLUI
	 * @param args 0 or 1 argument : the name of the restaurant that must be loaded from serialization
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String restaurantName = "default";
		if(args.length==1)
			restaurantName=args[0];
		//We create an instance of program from the restaurant name
		Program p = new Program(restaurantName);
		ProgramController controller = new ProgramController(p);
		controller.handleRequest("landing");
		//As long as the program runs, we handle the command given by the user
		while(true){
			controller.handleRequest(input.nextLine());
		}
	}
}
