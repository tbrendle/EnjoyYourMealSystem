
package clui;

/**
 * Displaying view when a user is added
 */
public class ConfirmSubView extends AbstractView {
	/**
	 * Display the view
	 * @param result not used here
	 */
	@Override
	public void show(Object result) {
		System.out.println("L'utilisateur a bien été ajouté");
	}
}
