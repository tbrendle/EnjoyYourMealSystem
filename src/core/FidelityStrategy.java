package core;
/**
 *
 * Interface fidelity cards have to implement
 *
 */
public interface FidelityStrategy {
	/**
	 * Pay using a fidelity card
	 * @param o the order to pay
	 * @return the price to pay
	 */
	public float pay(Order o);
	/**
	 * Get the price of an order having a fidelity card
	 * @param o the order
	 * @return the price of the order having this fidelity card
	 */
	public float getPrice(Order o);
	
	/**
	 * @return Hard Copy of the object
	 */
	public FidelityStrategy clone();
}
