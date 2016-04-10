
package clui;

import java.util.HashMap;

import core.Ingredient;

/**
 * Displaying a view when a user asked for the list of ingredients in a meal
 */
public class ListIngredientsView extends AbstractView {
	/**
	 * Displays the ingredient given in parameter
	 * @param result a HashMap containing the ingredients
	 */
	@Override
	public void show(Object result) {
		if(result instanceof HashMap){
			HashMap<String,  Ingredient> ingredients = (HashMap<String, Ingredient>) result;
			for(Ingredient i : ingredients.values()){
				System.out.println(i.getName()+"--- "+i.getQuantity()+"g");
			}
		}
		
	}

}
