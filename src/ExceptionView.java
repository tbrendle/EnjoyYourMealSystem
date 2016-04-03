
public class ExceptionView extends AbstractView {

	public ExceptionView(Program p) {
		super(p);
	}

	@Override
	public void show() {}
	
	public void show(String errorMessage){
		System.out.println("Woops, something bad happened");
		System.out.println(errorMessage);
	}

}
