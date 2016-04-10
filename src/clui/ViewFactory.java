package clui;

/**
 * Factory that creates our views
 */
public class ViewFactory {
	/**
	 * Creates a view given its name
	 * @param name the name of the view to create
	 * @return the View created by the factory
	 */
    public static AbstractView create(String name)
    {
    	AbstractView view = null;
		try {
			name = "clui."+name.substring(0,1).toUpperCase() + name.substring(1)+"View";
			view = (AbstractView) Class.forName(name).newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
		}
		return view;
    }
}
