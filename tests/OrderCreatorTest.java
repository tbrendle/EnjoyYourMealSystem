import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for OrderCreator and Order (every Order is born from an order creator)
 */
public class OrderCreatorTest {
	private OrderCreator c;
	private Customer u;
	private Restaurant r;
	
	/**
	 * Initialization method to create an order creator
	 */
	public OrderCreatorTest(){
		r = new Restaurant();
		Meal m = new Meal("Pates");
		Ingredient pates = new Ingredient("Pates");
		Ingredient beurre =  new Ingredient("Beurre");
		m.putIngredient(beurre, 10);
		m.putIngredient(pates, 200);
		m.setPrice(15);
		m.setSpecialPrice(10);
		r.addMeal(m);
		u = new Customer("Bob", "Red", "bobred", "123");
		c = new OrderCreator(r,u);
	}


	/**
	 * Test method for selecting and flushing meals
	 */
	@Test
	public void testSelectMeal() {
		c.selectMeal("Pates", 10);
		c.flushCurrentMeal();
		assertTrue(c.currentOrder().getMeals().size()==10);
	}

	/**
	 * Test method for personalizing meals in an order
	 */
	@Test
	public void testPersonalizeMeal() {
		for(int i = 0; i<10; i++){
			c.selectMeal("Pates", 1);
			c.personalizeMeal("Pates", 100*i);
			c.flushCurrentMeal();
		}
		Order o = c.currentOrder();
		for(int i = 0; i<10; i++){
			assertTrue(o.getMeals().get(i).getIngredientQuantity("Pates")== 200+i*100);
		}
	}


	/**
	 * Test method for saving orders
	 */
	@Test
	public void testSaveOrder() {
		for(int i = 0; i<10; i++){
			c.selectMeal("Pates", 1);
			c.personalizeMeal("Pates", 100*i);
			c.flushCurrentMeal();
		}
		Order o = c.saveOrder();
		for(int i = 0; i<10; i++){
			assertTrue(o.getMeals().get(i).getIngredientQuantity("Pates")== 200+i*100);
		}
	}


	/**
	 * Test method for evaluating the price of an order
	 */
	@Test
	public void testEvalPrice() {
		c.selectMeal("Pates", 10); //Costs 15*10 with BasicFidelityCard
		assertTrue(c.evalPrice()==150);
		u.setFidelityCard(CardFactory.create("Lottery"));
		assertTrue(c.evalPrice()==150);
		u.setFidelityCard(CardFactory.create("Point"));
		assertTrue(c.evalPrice()==150);
		r.getMeal("Pates").setPromotion(true);
		u.setFidelityCard(CardFactory.create("Basic"));
		assertTrue(c.evalPrice()==150);
		c.selectMeal("Pates", 10); //Costs 15*10 + 10*10 with BasicFidelityCard, or 15*20 for other cards
		assertTrue(c.evalPrice()==250);
		u.setFidelityCard(CardFactory.create("Lottery"));
		assertTrue(c.evalPrice()==300);
		u.setFidelityCard(CardFactory.create("Point"));
		assertTrue(c.evalPrice()==300);
	}

}
