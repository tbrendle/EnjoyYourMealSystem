package clui;
/**
 * 
 * Class AbstractView : general model for displaying views
 *
 */
public abstract class AbstractView {
	/**
	 * Display the view, using the informations given in result
	 * @param result the information to be used by the view
	 */
	public abstract void show(Object result);
}
