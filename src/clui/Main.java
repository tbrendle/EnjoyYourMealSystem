package clui;
import java.util.Scanner;

import core.Program;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String restaurantName = "default";
		if(args.length==1)
			restaurantName=args[0];
		Program p = new Program(restaurantName);
		ProgramController controller = new ProgramController(p);
		controller.handleRequest("landing");
		while(true){
			controller.handleRequest(input.nextLine());
		}
	}
}