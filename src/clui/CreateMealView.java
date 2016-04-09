package clui;

import core.Ingredient;
import core.Meal;

public class CreateMealView extends AbstractView {
	@Override
	public void show(Object o) {
		if(o instanceof Meal){
			Meal m = (Meal) o;
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
