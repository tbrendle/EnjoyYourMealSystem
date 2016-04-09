package clui;

import core.Meal;

public class SaveMealView extends AbstractView {
	@Override
	public void show(Object result) {
		if(result instanceof Meal){
			System.out.println(((Meal) result).getName()+" successfully saved");
		}
	}
}
