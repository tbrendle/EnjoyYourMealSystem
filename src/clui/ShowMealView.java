
package clui;

import java.util.HashMap;

import core.Meal;

public class ShowMealView extends AbstractView {

	@Override
	public void show(Object result) {
		System.out.println("Voici la liste des plats");
		if(result instanceof HashMap){
			HashMap<String, Meal> meals = (HashMap<String, Meal>) result;
			for(Meal m : meals.values()){
				System.out.println(m.getName() + " : " + m.getPrice() +"$"+ (m.isPromotion() ? " --- " + m.getSpecialPrice()+"$": ""));
			}
			System.out.println("Enjoy your meal !");
		}
		
	}


}
