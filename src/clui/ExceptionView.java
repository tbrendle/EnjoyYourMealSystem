package clui;

/**
 * Displaying view when an exception has occurred
 */
public class ExceptionView extends AbstractView {
	/**
	 * Display the error message corresponding to the exception that has occurred
	 * @param errorMessage the error message to display 
	 */
	public void show(Object errorMessage){
		System.out.println("Woops, something bad happened");
		System.out.println(errorMessage);
	}

}
