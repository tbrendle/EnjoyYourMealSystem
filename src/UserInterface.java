public abstract class UserInterface {
	public abstract String in();
	public String in(InputChecker checker){
		String input = in();
		while(!checker.check(input)){
			out("Error, invalid value");
			input = in();
		}
		return input;
	}
	public abstract void out(String s);
}
