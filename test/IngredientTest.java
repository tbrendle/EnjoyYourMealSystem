import static org.junit.Assert.*;

import org.junit.Test;

public class IngredientTest {
	private Ingredient i = new Ingredient("Mushroom");
	/**
	 * Test method for {@link Ingredient#Ingredient(java.lang.String)}.
	 */
	@Test
	public void testIngredientString() {
		i = new Ingredient("Mushroom");
		assertEquals(true, i instanceof Ingredient);
	}

	/**
	 * Test method for {@link Ingredient#Ingredient(java.lang.String, java.lang.Integer)}.
	 */
	@Test
	public void testIngredientStringInteger() {
		i = new Ingredient("Mushroom", 15);
		assertEquals(true, i instanceof Ingredient);
	}


	/**
	 * Test method for {@link Ingredient#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		Ingredient i1 = new Ingredient("Mushroom", 15);
		Ingredient i2 = new Ingredient("Beer", 10);
		assertEquals(i, i1);
		assertNotEquals(i, i2);
	}

	/**
	 * Test method for {@link Ingredient#getName()}.
	 */
	@Test
	public void testName() {
		i.setName("Beer");
		assertEquals(i.getName(), "Beer");
		i.setName("Mushroom");
		assertEquals(i.getName(), "Mushroom");
	}

	/**
	 * Test method for {@link Ingredient#getCostPerUnit()}.
	 */
	@Test
	public void testCostPerUnit() {
		i.setCostPerUnit(3);
		assertEquals(i.getCostPerUnit()==3, true);
	}

	/**
	 * Test method for {@link Ingredient#getQuantity()}.
	 */
	@Test
	public void testQuantity() {
		i.setQuantity(3);
		assertEquals(i.getQuantity()==3, true);
	}


	/**
	 * Test method for {@link Ingredient#setPersonalization(java.lang.Integer)}.
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
