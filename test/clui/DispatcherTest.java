package clui;
import static org.junit.Assert.*;

import org.junit.Test;

import clui.Dispatcher;

/**
 * Test class for dispatcher
 */
public class DispatcherTest {

	private Dispatcher d = new Dispatcher();
	
	/**
	 * Test that dispatching user to views does not raise errors
	 */
	@Test
	public void test() {
		d.dispatch("landing", null);
		d.dispatch("unknown route", null);
	}

}
