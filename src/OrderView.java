public class OrderView extends AbstractView {

	public OrderView(Program p) {
		super(p);
	}

	@Override
	public void show() {
		OrderCreator oc = program.currentOrder();
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
	}
	
	public void printOrder(Order o){
		o.getPrice();
		for(Meal m : o.getMeals()){	
			System.out.println(m.getName());
		}
		System.out.println("It will cost " + o.getPrice());
	}
	
	@Override
	public void show(Object o){
		if(o instanceof Order){
			System.out.println("Your order will be ready soon");
			printOrder((Order) o);
		} else {
			show();
		}
	}

}
