package clui;
import java.util.Scanner;

import core.Program;

public abstract class AbstractController {
	Program program;
	Dispatcher view;
	public AbstractController(Program p){
		program = p;
	}
}
