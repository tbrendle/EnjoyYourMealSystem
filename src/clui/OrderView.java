package clui;

import core.Ingredient;
import core.Meal;
import core.Order;
import core.OrderCreator;

public class OrderView extends AbstractView {

	@Override
	public void show(Object o){
		if(o instanceof OrderCreator){
			OrderCreator oc  = (OrderCreator) o;
			if(oc.currentOrder() == null)
				System.out.println("No order is currently selected");
			if(oc.getCurrentMeal()!=null){
				System.out.println("You have selected "+oc.getCurrentMeal().getName()+ " costing "+oc.evalPrice());
				for(Ingredient i : oc.getCurrentMeal().getIngredients().values()){
					System.out.println(i.getName()+"---"+i.getQuantity()+i.getPersonalization()+"g");
				}
				System.out.println("You can personalize your meal typing");
				System.out.println("personalizeMeal<ingredient, quantity>");
			}
			System.out.println("You can add a meal to your order typing");
			System.out.println("selectMeal<mealName, quantity>");
		} else if (o instanceof Order){
			System.out.println("Your order will be ready soon");
			printOrder((Order) o);
		}
	}
	
	public void printOrder(Order o){
		float noReductionPrice = 0;
		for(Meal m : o.getMeals()){	
			System.out.println(m.getName() + " --- "+(m.getPrice()+m.getExtraPrice())+ "$");
			noReductionPrice +=m.getPrice()+m.getExtraPrice();
		}
		System.out.println("Promotions : --- " + (o.getPrice()-noReductionPrice) +"$");
		System.out.println("It will cost " + o.getPrice()+"$");
	}
	

}
