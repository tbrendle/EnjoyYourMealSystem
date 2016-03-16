import java.io.Serializable;

/**
 *
 * Class for the ingredients of a meal
 *
 */
public class Ingredient implements Serializable{
	private String name;
	private Integer quantity;
	private Integer personalization;
	private float costPerUnit;
	/**
	 * Ingredient constructor
	 * @param name name of the ingredient
	 */
	public Ingredient(String name){
		this.name = name;
		// By default, no personalization of the quantity, and a cost per unit of 1
		this.quantity = 0;
		// Personalization is the delta set by the user to personalize his meal
		this.personalization = 0;
		this.setCostPerUnit(1);
	}
	/**
	 * Ingredient constructor for a personalized ingredient
	 * @param name name of the ingredient
	 * @param personalization quantity to add or remove
	 */
	public Ingredient(String name, Integer personalization){
		//TODO : handle negative quantity or null quantity
		this.name = name;
		this.quantity = 0;
		this.setCostPerUnit(1);
		this.personalization = personalization;
	}
	
	/**
	 * Test if a given object (Ingredient) is the same as this ingredient
	 * @param o an object (ingredient)
	 * @return true if the given object is the same as this ingredient, false otherwise
	 */
	@Override
	public boolean equals(Object o){
		if(o instanceof Ingredient)
			return name.equals(((Ingredient) o).getName());
		return false;
	}
	/**
	 * Get the name of this ingredient
	 * @return the name of this ingredient
	 */
	public String getName() {
		return name;
	}
	/**
	 * Set the name of this ingredient
	 * @param name the name of this ingredient
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Get the cost per unit of this ingredient
	 * @return the cost per unit of this ingredient
	 */
	public float getCostPerUnit() {
		return costPerUnit;
	}
	/**
	 * Set the cost per unit of this ingredient
	 * @param costPerUnit the cost per unit of this ingredient
	 */
	public void setCostPerUnit(float costPerUnit) {
		this.costPerUnit = costPerUnit;
	}
	/**
	 * Get the quantity of this ingredient
	 * @return the quantity of this ingredient
	 */
	public Integer getQuantity() {
		return quantity;
	}
	/**
	 * Set the quantity of this ingredient
	 * @param quantity the quantity of this ingredient
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	/**
	 * Set the personalization of this ingredient in quantity
	 * @param personalization the delta between the quantity associated to this ingredient and the quantity we want
	 */
	public void setPersonalization(Integer personalization){
		// TODO : check if personalization < quantity
		if(personalization+quantity<0)
			personalization = -quantity;
		this.personalization = personalization;
	}
	/**
	 * Get the personalization of this ingredient
	 * @return the personalization of this ingredient
	 */
	public Integer getPersonalization(){
		return this.personalization;
	}
	
}
