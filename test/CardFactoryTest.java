import static org.junit.Assert.*;

import org.junit.Test;

import core.BasicCard;
import core.CardFactory;
import core.FidelityStrategy;
import core.LotteryCard;
import core.PointCard;

/**
 * Test class for CardFactory
 */
public class CardFactoryTest {

	/**
	 * Test the creation of a basic card
	 */
	@Test
	public void BasicCardTest() {
		FidelityStrategy b = (BasicCard) CardFactory.create("Basic");
		assertTrue(b instanceof BasicCard);
	}
	/**
	 * Test the creation of a point card
	 */
	@Test
	public void PointCardTest() {
		FidelityStrategy b = (PointCard) CardFactory.create("Point");
		assertTrue(b instanceof PointCard);
	}
	
	/**
	 * Test the creation of a lottery card
	 */
	@Test
	public void LotteryCardTest() {
		FidelityStrategy b = (LotteryCard) CardFactory.create("Lottery");
		assertTrue(b instanceof LotteryCard);
	}
	
	/**
	 * Test the behavior when a card of an unknown type is asked to the factory
	 */
	@Test(expected=IllegalArgumentException.class)
	public void UnknownCardTest() {
		CardFactory.create("Unknow");
	}

}
