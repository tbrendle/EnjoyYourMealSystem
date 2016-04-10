package clui;

/**
 * Displaying class for when the router sent us to a non existing page (unknwon command)
 */
public class PageNotFoundView extends AbstractView {
	
	/**
	 * Display that the command entered doesn't correspond to any page
	 * @param result not used here
	 */
	@Override
	public void show(Object result) {
		System.out.println("This command does not exist");
	}
}
