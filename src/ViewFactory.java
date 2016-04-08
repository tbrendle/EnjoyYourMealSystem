public class ViewFactory {
    public static AbstractView create(String name)
    {
    	AbstractView view = null;
		try {
			name = name.substring(0,1).toUpperCase() + name.substring(1)+"View";
			view = (AbstractView) Class.forName(name).newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
		}
		return view;
    }
}
