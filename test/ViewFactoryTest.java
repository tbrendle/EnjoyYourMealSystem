import static org.junit.Assert.*;

import org.junit.Test;

import clui.AbstractView;
import clui.LoginView;
import clui.ViewFactory;

public class ViewFactoryTest {
	private ViewFactory factory = new ViewFactory();
	
	@Test
	public void LoginViewTest() {
		LoginView lv = (LoginView) factory.create("Login");
		LoginView lu = (LoginView) factory.create("login");
		assertNotNull(lu);
		assertNotNull(lv);
	}
	
	@Test
	public void UnknownViewTest() {
		AbstractView v = factory.create("UNNKNOWWN VIEW");
		assertNull(v);
	}
}
