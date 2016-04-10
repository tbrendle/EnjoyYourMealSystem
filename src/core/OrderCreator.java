package core;
/**
 * 
 * Class to manage the creation of a new order
 *
 */
public class OrderCreator {
	private Order order;
	private Restaurant restaurant;
	private Customer customer;
	private Meal currentMeal;
	private Integer currentMealQuantity;
	
	/**
	 * Order creator constructor
	 * @param r the restaurant in which the order is made
	 * @param c the customer making the order
	 */
	public OrderCreator(Restaurant r, Customer c){
		this.restaurant = r;
		this.customer = c;
	}
	
	/**
	 * Select a meal for this order
	 * @param mealName the name of the meal to select
	 * @param quantity the quantity of this meal we want
	 */
	public void selectMeal(String mealName, Integer quantity) {
		if(this.order == null)
			this.order = new Order(customer);
		flushCurrentMeal();
		if(restaurant.getMeal(mealName)==null){
			throw new IllegalArgumentException(mealName+" not found :/");
		}
		Meal meal = restaurant.getMeal(mealName).clone();
		currentMeal = meal;
		currentMealQuantity = quantity;
	}
	/**
	 * @return current meal
	 */
	public Meal getCurrentMeal(){
		return currentMeal;
	}
	/**
	 * Flush current(s) meal(s) and add them to order
	 */
	public void flushCurrentMeal(){
		if(currentMeal != null){
			for(int i = 0; i< currentMealQuantity; i++)
				order.addMeal(currentMeal);
		}
		currentMeal=null;
		currentMealQuantity=0;
	}
	
	/**
	 * Personalize the currentMeal from this order
	 * @param ingredientName the name of the ingredient to personalize in the meal
	 * @param quantity the quantity we want to add or remove
	 */
	public void personalizeMeal(String ingredientName, Integer quantity) {
		if(order==null){
			throw new IllegalArgumentException("Order not found :/");
		}
		if(currentMeal==null){
			throw new IllegalArgumentException("Meal not found :/");
		}
		currentMeal.personalize(ingredientName, quantity);
	}
	
	/**
	 * Personalize a meal from this order
	 * 
	 * @param ingredientName the name of the ingredient to personalize in the meal
	 * @param quantity the quantity we want to add or remove
	 * @param index the index of the meal in order
	 */
	public void personalizeMeal(String ingredientName, Integer quantity, Integer index) {
		if(order==null){
			throw new IllegalArgumentException("Order not found :/");
		}
		order.personalizeMealByIndex(ingredientName,quantity,index);
	}
	
	/**
	 * Save this order
	 * @return the order saved
	 */
	public Order saveOrder(){
		if(order==null){
			throw new IllegalArgumentException("Order not found :/");
		}
		flushCurrentMeal();
		order.setPrice(customer.getFidelityCard().pay(order));
		Order finalizedOrder = order;
		// We reinitialize the buffer order for creation being hold in the creator
		order = null;
		return finalizedOrder;
	}
	
	/**
	 * Gives the current order being made
	 * @return the current order being made
	 */
	public Order currentOrder() {
		return order;
	}

	/**
	 * Evaluate the price of the current order being made
	 * @return the price of the current order being made
	 */
	public float evalPrice() {
		if(order==null){
			throw new IllegalArgumentException("Order not found :/");
		}
		flushCurrentMeal();
		order.setPrice(customer.getFidelityCard().getPrice(order));
		return order.getPrice();
	}

}
