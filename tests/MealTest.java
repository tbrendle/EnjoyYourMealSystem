import static org.junit.Assert.*;

import org.junit.Test;


public class MealTest {
	private Meal m  =  new Meal("Salade");

	/**
	 * Test method for {@link Meal#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		Meal m1 = new Meal("Salade");
		assertEquals(m,m1);
	}

	/**
	 * Test method for {@link Meal#getIngredients()}.
	 */
	@Test
	public void testIngredients() {
		Ingredient i1 = new Ingredient("Letuce");
		Ingredient i2 = new Ingredient("Tomato");
		m.putIngredient(i2, 40);
		m.putIngredient(i1, 20);
		assertTrue(m.getIngredientQuantity("Letuce")==20);
		assertTrue(m.getIngredientQuantity(i1)==20);
	}

	/**
	 * Test method for {@link Meal#getName()}.
	 */
	@Test
	public void testName() {
		m.setName("Pasta");
		assertEquals(m.getName(), "Pasta");
	}

	/**
	 * Test method for {@link Meal#getPrice()}.
	 */
	@Test
	public void testPrice() {
		m.setPrice(123);
		assertTrue(m.getPrice()==123);
		m.setSpecialPrice(120);
		assertTrue(m.getSpecialPrice()==120);
		m.setPromotion(true);
		assertTrue(m.isPromotion());
	}


	/**
	 * Test method for {@link Meal#personalize(java.lang.String, java.lang.Integer)}.
	 */
	@Test
	public void testPersonalize() {
		Ingredient i1 = new Ingredient("Letuce");
		Ingredient i2 = new Ingredient("Tomato");
		m.putIngredient(i1, 20);
		m.putIngredient(i2, 40);
		m.personalize("Letuce", 24);
		assertTrue(m.getExtraPrice()==i1.getCostPerUnit()*24);
		m.personalize("Letuce", -20);
		assertNull(m.getIngredients().get("Letuce"));
	}

}
