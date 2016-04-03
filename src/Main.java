import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String restaurantName = "default";
		if(args.length==1)
			restaurantName=args[0];
		Program p = new Program("random", "randomOut");
		p.registerCustomer("bob", "red", "yolo", "lolz");
		ProgramController controller = new ProgramController(p);
		controller.handleRequest("createMeal raclette 2.3");
		controller.handleRequest("landing");
		while(true){
			controller.handleRequest(input.nextLine());
		}
	}
}
