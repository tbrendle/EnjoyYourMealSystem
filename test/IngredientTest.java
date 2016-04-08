import static org.junit.Assert.*;

import org.junit.Test;

import core.Ingredient;

/**
 * Test class for ingredient
 */
public class IngredientTest {
	private Ingredient i = new Ingredient("Mushroom");
	/**
	 * Test method for creating a new ingredient
	 */
	@Test
	public void testIngredientString() {
		i = new Ingredient("Mushroom");
		assertEquals(true, i instanceof Ingredient);
	}

	/**
	 * Test method for a new ingredient with a quantity
	 */
	@Test
	public void testIngredientStringInteger() {
		i = new Ingredient("Mushroom", 15);
		assertEquals(true, i instanceof Ingredient);
	}


	/**
	 * Test method for testing the equality of two ingredients
	 */
	@Test
	public void testEqualsObject() {
		Ingredient i1 = new Ingredient("Mushroom", 15);
		Ingredient i2 = new Ingredient("Beer", 10);
		assertEquals(i, i1);
		assertNotEquals(i, i2);
	}

	/**
	 * Test method for setting and getting ingredient's name
	 */
	@Test
	public void testName() {
		i.setName("Beer");
		assertEquals(i.getName(), "Beer");
		i.setName("Mushroom");
		assertEquals(i.getName(), "Mushroom");
	}

	/**
	 * Test method for setting and getting an ingredient's cost per unit
	 */
	@Test
	public void testCostPerUnit() {
		i.setCostPerUnit(3);
		assertEquals(i.getCostPerUnit()==3, true);
	}

	/**
	 * Test method for setting and getting an ingredient quantity
	 */
	@Test
	public void testQuantity() {
		i.setQuantity(3);
		assertEquals(i.getQuantity()==3, true);
	}


	/**
	 * Test method for personalizing an ingredient
	 */
	@Test
	public void testPersonalization() {
		Integer q = -i.getQuantity();
		i.setPersonalization(13);
		assertEquals(i.getPersonalization()==13, true);
		i.setPersonalization(q-10);
		assertEquals(i.getPersonalization(),q);
		
	}


}
