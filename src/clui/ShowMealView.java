
package clui;

import java.util.ArrayList;
import java.util.HashMap;

import core.Meal;
import core.ScorableMeal;

public class ShowMealView extends AbstractView {

	@Override
	public void show(Object result) {
		System.out.println("Voici la liste des plats");
		if(result instanceof HashMap){
			HashMap<String, Meal> meals = (HashMap<String, Meal>) result;
			for(Meal m : meals.values()){
				displayMeal(m);
			}
			System.out.println("Enjoy your meal !");
		} else if (result instanceof ArrayList){
			ArrayList<ScorableMeal> meals = (ArrayList<ScorableMeal>) result;
			for(ScorableMeal m : meals){
				displayMeal(m);
			}
		}
		
	}

	public void displayMeal(Meal m){
		System.out.println(m.getName() + " : " + m.getPrice() +"$"+ (m.isPromotion() ? " --- " + m.getSpecialPrice()+"$": ""));
	}
	
	public void displayMeal(ScorableMeal m){
		if(m instanceof ScorableMeal){
			System.out.println(m.getName() + " : " + m.getScore());
		}
	}

}
