import static org.junit.Assert.*;

import org.junit.Test;

public class TestScenario {
	private Program program = new Program("restaurant");

	@Test 
	public void testCreateBobRed(){
		try{
			program.insertChef("Bob", "Red", "bobred", "123456");
			program.saveData();
		} catch (Exception e){
			
		}
	}
	@Test
	public void testAuth(){
		program.authenticateUser("bobred", "123456");
	}
	
	@Test
	public void testCreateMeal(){
		program.authenticateUser("bobred", "123456");
		program.createMeal("Raclette", 20);
	}
	
	@Test
	public void testAddIngredient(){
		program.authenticateUser("bobred", "123456");
		program.createMeal("Raclette", 20);
		program.addIngredient("cheese", 80);
		program.addIngredient("ham", 40);
		program.addIngredient("potatoes", 50);
	}
	
	
	@Test
	public void testCurrentMeal(){
		program.authenticateUser("bobred", "123456");
		program.createMeal("Raclette", 20);
		program.addIngredient("cheese", 80);
		program.addIngredient("ham", 40);
		program.addIngredient("potatoes", 50);
		Meal currentMeal = program.currentMeal();
		assertEquals(currentMeal.getName(), "Raclette");
		assertEquals(currentMeal.getIngredients().keySet().size(), 3);
		assertEquals(currentMeal.getIngredientQuantity("cheese")==80, true);
	}
	
	@Test
	public void testSaveMeal(){
		program.authenticateUser("bobred", "123456");
		program.createMeal("Raclette", 20);
		program.addIngredient("cheese", 80);
		program.addIngredient("ham", 40);
		program.addIngredient("potatoes", 50);
		program.saveMeal();
		Meal currentMeal = program.getAvailableMeals().get("Raclette");
		assertEquals(currentMeal.getName(), "Raclette");
		assertEquals(currentMeal.getIngredients().keySet().size(), 3);
		assertEquals(currentMeal.getIngredientQuantity("cheese") ==80, true);
	}
	
	@Test
	public void test1(){
		program.authenticateUser("bobred", "123456");
		program.createMeal("Raclette", 20);
		program.addIngredient("cheese", 80);
		program.addIngredient("ham", 40);
		program.addIngredient("potatoes", 50);
		program.saveMeal();
		program.createMeal("Cheeeeese", 20);
		program.addIngredient("cheese", 280);
		program.saveMeal();
		program.createMeal("Cheeeese", 20);
		program.addIngredient("cheese", 280);
		program.saveMeal();
		program.createMeal("Cheese", 20);
		program.addIngredient("cheese", 280);
		program.saveMeal();
		program.createMeal("Chese", 20);
		program.addIngredient("cheese", 280);
		program.saveMeal();
		program.createMeal("Cheeeceese", 20);
		program.addIngredient("cheese", 280);
		program.saveMeal();
		program.createMeal("Cheeeessese", 20);
		program.addIngredient("cheese", 280);
		program.saveMeal();
		program.createMeal("Cheeeeddese", 20);
		program.addIngredient("cheese", 280);
		program.saveMeal();
		program.createMeal("Chedfggfeeeese", 20);
		program.addIngredient("cheese", 280);
		program.saveMeal();
		program.listIngredients("Raclette");
		program.registerClient("Mario", "Rossi", "Mario", "345678");
		program.authenticateUser("Mario", "345678");
		
	}
	
	/*
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
	}*/


}
