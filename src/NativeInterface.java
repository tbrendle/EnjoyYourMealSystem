import java.util.Scanner;

public class NativeInterface extends UserInterface {
	private Scanner scanner;
	
	public NativeInterface(){
		this.scanner = new Scanner(System.in);
	}
	
	@Override
	public void out(String s) {
		System.out.println(s);
	}

	@Override
	public String in() {
		return scanner.nextLine();
	}

}
