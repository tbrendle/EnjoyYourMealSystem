import static org.junit.Assert.*;

import org.junit.Test;


public class CardFactoryTest {

	@Test
	public void BasicCardTest() {
		FidelityStrategy b = (BasicCard) CardFactory.create("Basic");
		assertTrue(b instanceof BasicCard);
	}
	
	@Test
	public void PointCardTest() {
		FidelityStrategy b = (PointCard) CardFactory.create("Point");
		assertTrue(b instanceof PointCard);
	}
	
	@Test
	public void LotteryCardTest() {
		FidelityStrategy b = (LotteryCard) CardFactory.create("Lottery");
		assertTrue(b instanceof LotteryCard);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void UnknownCardTest() {
		FidelityStrategy b = CardFactory.create("Unknow");
	}

}
