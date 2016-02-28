
public class OrderCreator implements OrderCreationInterface{
	private Order order;
	private Restaurant restaurant;
	private Customer customer;
	
	public OrderCreator(Restaurant r, Customer c){
		this.restaurant = r;
		this.customer = c;
	}
	
	@Override
	public void selectMeal(String mealName, Integer quantity) {
		if(this.order == null)
			this.order = new Order();
		Meal meal = restaurant.getMeal(mealName);
		if(meal==null){
			throw new IllegalArgumentException(mealName+" not found :/");
		}
		for(int i = 0; i< quantity; i++)
			order.addMeal(meal);
	}

	@Override
	public void personalizeMeal(String mealName, String ingredientName, Integer quantity) {
		//TODO: ask for clarification
		if(order==null){
			throw new IllegalArgumentException("Order not found :/");
		}
	}
	
	@Override
	public Order saveOrder(){
		if(order==null){
			throw new IllegalArgumentException("Order not found :/");
		}
		order.setPrice(customer.getFidelityCard().pay(order));
		Order finalizedOrder = order;
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
