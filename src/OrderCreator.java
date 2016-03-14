/**
 * 
 * Class to manage the creation of a new order
 *
 */
public class OrderCreator implements OrderCreationInterface{
	private Order order;
	private Restaurant restaurant;
	private Customer customer;
	
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
	@Override
	public void selectMeal(String mealName, Integer quantity) {
		if(this.order == null)
			this.order = new Order(customer);
		Meal meal = restaurant.getMeal(mealName);
		if(meal==null){
			throw new IllegalArgumentException(mealName+" not found :/");
		}
		for(int i = 0; i< quantity; i++)
			order.addMeal(meal);
	}

	/**
	 * Personalize a meal from this order
	 * @param mealName the name of the meal to personalize
	 * @param ingredientName the name of the ingredient to personalize in the meal
	 * @param quantity the quantity we want to personalize by
	 */
	@Override
	public void personalizeMeal(String mealName, String ingredientName, Integer quantity) {
		//TODO: ask for clarification
		if(order==null){
			throw new IllegalArgumentException("Order not found :/");
		}
	}
	
	/**
	 * Save this order
	 */
	@Override
	public Order saveOrder(){
		if(order==null){
			throw new IllegalArgumentException("Order not found :/");
		}
		order.setPrice(customer.getFidelityCard().pay(order));
		Order finalizedOrder = order;
		// We reinitialize the buffer order for creation being hold in the creator
		order = null;
		return finalizedOrder;
	}

	@Override
	public Order currentOrder() {
		return order;
	}

	@Override
	public float evalPrice() {
		if(order==null){
			throw new IllegalArgumentException("Order not found :/");
		}
		return customer.getFidelityCard().getPrice(order);
	}

}
