
public interface OrderCreationInterface {
	public void selectMeal(String mealName, Integer quantity);
	public void personalizeMeal(String mealName, String ingredientName, Integer quantity);
	public Order currentOrder();
	public Order saveOrder();
	public float evalPrice();
}
