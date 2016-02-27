import static org.junit.Assert.*;

import java.util.HashMap;
import org.junit.Test;

public class TestScenario {
	@Test
	public void test(){
		testWorkFlow();
	
	}
	@Test
	public void testIntegerChecker(){
		IntegerChecker ic = new IntegerChecker(null, null);
		assertEquals(ic.check("BJRE"), false);
		assertEquals(ic.check("345"), true);
		assertEquals(ic.check("345sdg"), false);
		assertEquals(ic.check("345 dghfg"), false);
		ic = new IntegerChecker(1, 10);
		assertEquals(ic.check("BJRE"), false);
		assertEquals(ic.check("345"), false);
		assertEquals(ic.check("3"), true);
		assertEquals(ic.check("345 dghfg"), false);
	}
	public void testWorkFlow(){
		Restaurant restaurant = testRestaurantCreation(); 
		Meal meal = restaurant.getMeal("Menu3");
		String ingredient = "Ingredient1";
		if(!ingredient.equals("")){
			String personalization = "PERSO";
			meal.personalize(ingredient, personalization);
		}
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
		meal.putIngredient(new Ingredient("Pomme"), 5);
		meal.putIngredient(new Ingredient("Rouleau de Pâte"), 2);
		assertEquals(2, meal.getIngredients().size());
		return meal;
	}
	
	public HashMap<String, Meal> generateMealList(){
		String[] meals = {"Menu1", "Menu2", "Menu3", "Menu4"};
		Ingredient[] ingredients = {new Ingredient("Ingredient1"), new Ingredient("Ingredient2"),new Ingredient("Ingredient3"),new Ingredient("Ingredient4")};
		HashMap<String, Meal> availableMeals = new HashMap<String,Meal>();
		float i = 0;
		Meal tempMeal;
		for(String m : meals){
			i++;
			tempMeal = new Meal(m);
			tempMeal.setPrice(10*i);
			tempMeal.setSpecialPrice(10*i-1/2);
			tempMeal.setPromotion(i%2==0);
			tempMeal.putIngredient(ingredients[(int) (i%4)], (int) i%4+1);
			i++;
			tempMeal.putIngredient(ingredients[(int) (i%4)], (int) i%4+1);
			i++;
			tempMeal.putIngredient(ingredients[(int) (i%4)], (int) i%4+1);
			availableMeals.put(tempMeal.getName(), tempMeal);
		}
		return availableMeals;
		
	}
	
	public Restaurant testRestaurantCreation(){
		Restaurant res = new Restaurant();
		res.setMeals(generateMealList());
		return res;
	}
	
	public void testOrder(){
		OrderManager om = new OrderManager(testRestaurantCreation(), testCustomerCreation(), new NativeInterface());
		om.run();
	}
	

}
