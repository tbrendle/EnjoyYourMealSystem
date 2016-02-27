public class OrderManager implements BasicManager{
	private Order order;
	private Restaurant restaurant;
	private UserInterface ui;
	private Customer user;
	public OrderManager(Restaurant restaurant, Customer user, UserInterface ui){
		this.order = new Order();
		this.restaurant = restaurant;
		this.ui = ui;
		this.user = user;
	}
	
	@Override
	public void run() {
		String answer = "";
		if(order.getMeals().size()>0){
			ui.out("Want another meal ? (y/n)");
			answer = ui.in();
			while(answer != "y" && answer != "n")
				answer = ui.in();
			if(answer == "n")
				finalize();
		}
		askForMeal();
	}
	public void askForMeal(){
		ui.out("Add a meal to your order:");
		for(Meal m : restaurant.getMeals()){
			ui.out(m.toString());
		}
		String mealName = ui.in(new SetChecker<Meal>(restaurant.getMeals(), true));
		if(mealName.equals("")){
			finalize();
		} else {
			Meal meal = restaurant.getExistingMealByString(mealName);
			ui.out("How much");
			int howmuch = 2;
			for(int i = 0; i<howmuch; i++){
				order.addMeal(personalize(meal));
			}
			ui.out("It will cost "+user.getFidelityCard().getPrice(order));
		}
	}
	public Meal personalize(Meal meal){
		ui.out((order.getMeals().size()+1)+" - Which ingredient do you want to personalize in "+ meal.getName());
		String ingredient = ui.in(new SetChecker<String>(meal.getIngredients().keySet(), true));
		if(!ingredient.equals("")){
			ui.out("Enter a personalization");
			String personalization = ui.in();
			//TODO: DO something with that
			return personalize(meal);
		} 
		return meal;
	}
	public void finalize(){
		order.setPrice(user.getFidelityCard().pay(order));
		ui.out("Your ordered:");
		for(Meal m : order.getMeals()){
			ui.out(m.toString());
		}
		ui.out("It cost"+ order.getPrice());
	}



}
