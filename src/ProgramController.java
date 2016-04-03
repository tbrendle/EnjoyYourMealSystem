import java.util.Scanner;

public class ProgramController {
	private Program program;
	private Dispatcher dispatcher;
	private Scanner input = new Scanner(System.in);
	public ProgramController(Program p){
		this.program=p;
		this.dispatcher = new Dispatcher(p);
	}
	
	public void handleRequest(String request) {
		dispatcher.dispatch(request);
		//waitNewRequest();
	}
	private void waitNewRequest() {
		handleRequest(input.nextLine());
	}
}
