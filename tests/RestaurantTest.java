import static org.junit.Assert.*;

import org.junit.Test;


public class RestaurantTest {
	private Restaurant r = new Restaurant();
	/**
	 * Test method for {@link Restaurant#getOrdersHistory()}.
	 */
	@Test
	public void testOrdersHistory() {
		Customer c = new Customer("Bob", "Red", "bobred", "123");
		Order o1 = new Order(c);
		Order o2 = new Order(c);
		r.addOrder(o1);
		r.addOrder(o2);
		assertTrue(r.getOrdersHistory().size()==2);
	}

	/**
	 * Test method for {@link Restaurant#getMeals()}.
	 */
	@Test
	public void testMeals() {
		Meal m1 = new Meal("Chocolat");
		Meal m2 = new Meal("Cacao");
		r.addMeal(m1);
		r.addMeal(m2);
		assertTrue(r.getMeals().size()==2);
		assertEquals(r.getMeal("Cacao").getName(), "Cacao");
	}


	/**
	 * Test method for {@link Restaurant#putAdmin(Admin)}.
	 */
	@Test
	public void testAdmin() {
		Admin c = new Admin("Bob", "Red", "bobred", "123");
		r.putAdmin(c);
		assertEquals(r.getAdmins().get("bobred"), c);
	}


	/**
	 * Test method for {@link Restaurant#putUser(Customer)}.
	 */
	@Test
	public void testUser() {
		Customer c = new Customer("Bob", "Red", "bobredc", "123");
		r.putUser(c);
		assertEquals(r.getUsers().get("bobredc"), c);
	}

}
