package clui;

import core.Meal;

/**
 * Displaying class for when a meal has been saved
 */
public class SaveMealView extends AbstractView {
	/**
	 * Displays that the meal was successfully saved
	 * @param result not used here
	 */
	@Override
	public void show(Object result) {
		if(result instanceof Meal){
			System.out.println(((Meal) result).getName()+" successfully saved");
		}
	}
}
