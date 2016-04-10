
package clui;

import java.util.ArrayList;
import java.util.HashMap;

import core.Meal;
import core.ScorableMeal;

/**
 * Displaying class for when the list of meals are asked
 */
public class ShowMealView extends AbstractView {

	/**
	 * Displays the list of available meals
	 * @param result either an HashMap or a ArrayList containing the list of meals 
	 */
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

	/**
	 * Displays a given meal and its details
	 * @param m the meal to display
	 */
	public void displayMeal(Meal m){
		System.out.println(m.getName() + " : " + m.getPrice() +"$"+ (m.isPromotion() ? " --- " + m.getSpecialPrice()+"$": ""));
	}
	
	/**
	 * Displays a given ScorableMeal : the meal name and its score
	 * @param m the ScorableMeal to display
	 */
	public void displayMeal(ScorableMeal m){
		if(m instanceof ScorableMeal){
			System.out.println(m.getName() + " : " + m.getScore());
		}
	}

}
