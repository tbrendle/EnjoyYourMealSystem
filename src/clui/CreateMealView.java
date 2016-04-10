package clui;

import core.Ingredient;
import core.Meal;

/**
 * Displaying view when a meal is being created
 */
public class CreateMealView extends AbstractView {
	/**
	 * Display view when a meal is being created or edited : show the informations and ask for new ones
	 * @param result the meal being created or modified
	 */
	@Override
	public void show(Object result) {
		if(result instanceof Meal){
			Meal m = (Meal) result;
			System.out.println("You are editing a meal");
			System.out.println("Name :" + m.getName());
			System.out.println("Price :" + m.getPrice());
			System.out.println("Promotion price :" + m.getSpecialPrice());
			for(Ingredient i : m.getIngredients().values()){
				System.out.println("Name : "+i.getName()+" --- Quantity: "+i.getQuantity());
			}
			System.out.println("addIngredient<Name, Quantity in grams>");
			System.out.println("saveMeal: to save meal");
		}
	}

}
