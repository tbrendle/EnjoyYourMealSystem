public class MealCreator implements MealCreationInterface {
	public Meal meal;
	public void createMeal(String mealName, float price){
		meal = new Meal(mealName);
		meal.setPrice(price);
		meal.setSpecialPrice(price);
	}
	public void addIngredient(String ingredientName, Integer quantity){
		if(meal!=null)
			meal.putIngredient(new Ingredient(ingredientName), quantity);
		else
			throw new IllegalStateException("No meal...");
	}
	
	public Meal currentMeal(){
		return meal;
	}
	
	public Meal saveMeal(){
		Meal mealToReturn = meal;
		meal = null;
		return mealToReturn;
	}
}
