
package clui;

import java.util.HashMap;

import core.Ingredient;

public class ListIngredientsView extends AbstractView {

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
