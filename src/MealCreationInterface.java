
public interface MealCreationInterface {
	public void createMeal(String mealName, float price);
	public void addIngredient(String ingredientName, Integer quantity);
	public Meal currentMeal();
	public Meal saveMeal();
}
