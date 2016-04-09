package clui;
import static org.junit.Assert.*;

import org.junit.Test;

import clui.Dispatcher;

public class DispatcherTest {

	private Dispatcher d = new Dispatcher();
	@Test
	public void test() {
		d.dispatch("landing", null);
		d.dispatch("unknown route", null);
	}

}
