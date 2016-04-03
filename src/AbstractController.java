import java.util.Scanner;

public abstract class AbstractController {
	Program program;
	Dispatcher view;
	public AbstractController(Program p){
		program = p;
	}
}
