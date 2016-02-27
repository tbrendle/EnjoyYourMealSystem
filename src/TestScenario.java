import static org.junit.Assert.*;

import org.junit.Test;

public class TestScenario {
	@Test
	public void test(){
		testOrder();
	}
	
	public Customer testCustomerCreation() {
		Customer user = new Customer("Bob", "Red", "bobred");
		user.setPassword("123456");
		user.putContact("email","bob.red@gmail.com");
		user.putContact("phone", "0147200001");
		assertEquals("Bob Red", user.getFullName());
		assertEquals(true, user.authenticate("123456"));
		assertEquals(false, user.authenticate("123457"));
		assertEquals("bob.red@gmail.com", user.getPreferedContact());
		user.setPreferedContactType("phone");
		assertEquals("0147200001", user.getPreferedContact());
		Meal meal = testMealCreation();
		user.addFavoriteMeal(meal);
		assertEquals(1, user.getFavoriteMeals().size());
		Meal meal2 = testMealCreation();
		user.addFavoriteMeal(meal2);
		assertEquals(1, user.getFavoriteMeals().size());
		return user;
		
	}
	
	public Meal testMealCreation(){
		Meal meal = new Meal("Tarte aux pommes");
		assertEquals("Tarte aux pommes", meal.getName());
		meal.putIngredient("Pomme", 5);
		meal.putIngredient("Rouleau de Pâte", 2);
		assertEquals(2, meal.getIngredients().size());
		return meal;
	}
	
	public Restaurant testRestaurantCreation(){
		//TODO
		return new Restaurant();
	}
	
	public void testOrder(){
		OrderManager om = new OrderManager(testRestaurantCreation(), testCustomerCreation(), new NativeInterface());
		om.run();
	}
	

}
