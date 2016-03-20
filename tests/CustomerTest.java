import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

/**
 * Test class for customer
 */
public class CustomerTest {
	private Customer c = new Customer("Bob", "Red", "bobred", "123");

	/**
	 * Test method for Customer.isSpam
	 */
	@Test
	public void testSpam() {
		c.setSpam(true);
		assertEquals(c.isSpam(), true);
		c.setSpam(false);
		assertEquals(c.isSpam(), false);
	}

	/**
	 * Test method for customers setting and getting contacts
	 */
	@Test
	public void testGetContacts() {
		c.putContact("email", "bob@red.com");
		c.putContact("phone", "0645369230");
		assertEquals(c.getContacts().get("phone"), "0645369230");
		assertEquals(c.getContacts().get("email"), "bob@red.com");
	}

	/**
	 * Test method for setting and getting a user preferred contact type
	 */
	@Test
	public void testPreferredContactType() {
		c.setPreferredContactType("phone");
		assertEquals(c.getPreferredContactType(), "phone");
	}

	/**
	 * Test method for customer favorite meals
	 * Currently we have no informations about what is a favorite meal. 
	 * So we do not do anything with this parameter
	 */
	@Test
	public void testFavoriteMeals() {
	}

	/**
	 * Test method for setting and getting a fidelity card
	 */
	@Test
	public void testFidelityCard() {
		c.setFidelityCard(CardFactory.create("Basic"));
		assertTrue(c.getFidelityCard() instanceof BasicCard);
	}

	/**
	 * Test method for setting and getting a user's birthday
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testBirthDay() {
		c.setBirthDay(new Date(93, 11, 7));
		assertTrue(c.getBirthDay().getDate()==7);
	}

	/**
	 * Test method for sending a message to a customer
	 */
	@Test
	public void testSendMessage() {
		c.putContact("email", "bob@red.com");
		c.setPreferredContactType("email");
		c.sendMessage("bonjour");
	}

	/**
	 * Test method for getting a user's first, last, user and full name
	 */
	@Test
	public void testName() {
		assertEquals(c.getFirstName(), "Bob");
		assertEquals(c.getLastName(), "Red");
		assertEquals(c.getFullName(), "Bob Red");
		assertEquals(c.getUserName(), "bobred");
	}

	/**
	 * Test method for authenticating a user with its password
	 */
	@Test
	public void testAuthenticate() {
		assertTrue(c.authenticate("123"));
		assertFalse(c.authenticate("1234"));
	}

	/**
	 * Test method for setting a user password
	 */
	@Test
	public void testSetPassword() {
		c.setPassword("1234");
		assertTrue(c.authenticate("1234"));
		assertFalse(c.authenticate("123"));
	}

}
