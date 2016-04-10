package clui;

import core.Ingredient;
import core.Meal;
import core.Order;
import core.OrderCreator;

/**
 * Displaying class for when an order is being processed
 */
public class OrderView extends AbstractView {
	/**
	 * Displays the correct information during the process of ordering
	 * @param result either the current meal, the current order, depending on the current step of the process
	 */
	@Override
	public void show(Object result){
		if(result instanceof OrderCreator){
			//If o is an not null order creator, we display the current meal and suggest to select a new meal
			OrderCreator oc  = (OrderCreator) result;
			if(oc.currentOrder() == null)
				System.out.println("No order is currently selected");
			if(oc.getCurrentMeal()!=null){
				printMeal(oc.getCurrentMeal());
			}
			System.out.println("You can add a meal to your order typing");
			System.out.println("selectMeal<mealName, quantity>");
		} else if (result instanceof Meal){
			//If o is a Meal, we display it
			printMeal((Meal) result);
		} else if (result instanceof Order){
			//If o is an Order, we display it
			System.out.println("Your order will be ready soon");
			printOrder((Order) result);
		}
	}
	
	/**
	 * Displays the details of an order
	 * @param o the order to display
	 */
	public void printOrder(Order o){
		float noReductionPrice = 0;
		for(Meal m : o.getMeals()){	
			System.out.println(m.getName() + " --- "+(m.getPrice()+m.getExtraPrice())+ "$");
			noReductionPrice +=m.getPrice()+m.getExtraPrice();
		}
		System.out.println("Promotions : --- " + (o.getPrice()-noReductionPrice) +"$");
		System.out.println("It will cost " + o.getPrice()+"$");
	}
	
	/**
	 * Displays the details of a meal
	 * @param m the meal to display
	 */
	public void printMeal(Meal m){
		System.out.println("You have selected "+m.getName()+ " costing "+(m.getPrice()+m.getExtraPrice()));
		for(Ingredient i : m.getIngredients().values()){
			System.out.println(i.getName()+"---"+(i.getQuantity()+i.getPersonalization())+"g");
		}
		System.out.println("You can personalize your meal typing");
		System.out.println("personalizeMeal<ingredient, quantity>");
	}	
	

}
