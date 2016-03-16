/**
 * 
 * Class to manage the creation of a new meal
 *
 */
public class MealCreator {
	public Meal meal;
	/**
	 * Function to create a meal from a name and a price
	 * @param mealName the name of the meal to create
	 * @param price the price to set for the meal
	 */
	public void createMeal(String mealName, float price){
		//TODO: check if not existing ?
		// By default, the special price is the same as the real price (avoids a special price of 0)
		meal = new Meal(mealName);
		meal.setPrice(price);
		meal.setSpecialPrice(price);
	}
	/**
	 * Add an ingredient to the meal
	 * @param ingredientName the name of the ingredient to add
	 * @param quantity the quantity associated to this element
	 */
	public void addIngredient(String ingredientName, Integer quantity){
		if(meal!=null)
			meal.putIngredient(new Ingredient(ingredientName), quantity);
		else
			throw new IllegalStateException("No meal...");
	}
	
	/**
	 * Getting the meal currently created as it is at the moment
	 * @return the state of the meal currently created
	 */
	public Meal currentMeal(){
		return meal;
	}
	
	/**
	 * Saving a new meal to the restaurant
	 * @return the meal newly created
	 */
	public Meal saveMeal(){
		Meal mealToReturn = meal;
		// We reinitialize the buffer meal for creation being hold in the creator
		meal = null;
		return mealToReturn;
	}
}