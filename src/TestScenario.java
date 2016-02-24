import static org.junit.Assert.*;

import org.junit.Test;

public class TestScenario {
	@Test
	public void testUserCreation() {
		User user = new User("Bob", "Red", "bobred");
		user.setPassword("123456");
		user.putContact("email","bob.red@gmail.com");
		user.putContact("phone", "0147200001");
		assertEquals("Bob Red", user.getFullName());
		assertEquals(true, user.authenticate("123456"));
		assertEquals(false, user.authenticate("123457"));
		assertEquals("bob.red@gmail.com", user.getPreferedContact());
		user.setPreferedContactType("phone");
		assertEquals("0147200001", user.getPreferedContact());
	}

}
