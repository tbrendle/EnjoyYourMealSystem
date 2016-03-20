import static org.junit.Assert.*;

import org.junit.Test;


public class MealCreatorTest {
	MealCreator mc = new MealCreator();
	/**
	 * Test method for {@link MealCreator#createMeal(java.lang.String, float)}.
	 */
	@Test
	public void testCreateMeal() {
		mc.createMeal("KartofelnSalad", 20);
		assertEquals(mc.currentMeal().getName(), "KartofelnSalad");
		assertTrue(mc.currentMeal().getPrice()==20);
		
	}

	/**
	 * Test method for {@link MealCreator#addIngredient(java.lang.String, java.lang.Integer)}.
	 */
	@Test
	public void testAddIngredient() {
		mc.createMeal("KartofelnSalad", 20);
		mc.addIngredient("Potatoes", 20);
		mc.addIngredient("Tomatoes", 30);
		assertEquals(mc.currentMeal().getName(), "KartofelnSalad");
		assertTrue(mc.currentMeal().getPrice()==20);
		assertTrue(mc.currentMeal().getIngredientQuantity("Potatoes")==20);
		assertTrue(mc.currentMeal().getIngredientQuantity("Tomatoes")==30);
		assertTrue(mc.currentMeal().getIngredientQuantity("Salad")==0);
	}

	/**
	 * Test method for {@link MealCreator#saveMeal()}.
	 */
	@Test
	public void testSaveMeal() {		
		mc.createMeal("KartofelnSalad", 20);
		mc.addIngredient("Potatoes", 20);
		mc.addIngredient("Tomatoes", 30);
		Meal savedMeal = mc.saveMeal();
		assertNull(mc.currentMeal());
		assertEquals(savedMeal.getName(), "KartofelnSalad");
		assertTrue(savedMeal.getPrice()==20);
		assertTrue(savedMeal.getIngredientQuantity("Potatoes")==20);
		assertTrue(savedMeal.getIngredientQuantity("Tomatoes")==30);
		assertTrue(savedMeal.getIngredientQuantity("Salad")==0);
	}

}
