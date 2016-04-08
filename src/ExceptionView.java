
public class ExceptionView extends AbstractView {
	
	public void show(Object errorMessage){
		System.out.println("Woops, something bad happened");
		System.out.println(errorMessage);
	}

}
