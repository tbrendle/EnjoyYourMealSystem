import java.io.Serializable;
import java.util.HashMap;

/**
 * 
 * Class for the meals
 *
 */
public class Meal implements Serializable{
	private String name;
	private HashMap<String, Ingredient> ingredients;
	private float price;
	private float specialPrice;
	private boolean promotion;
	
	/**
	 * Meal constructor
	 * @param name the name of the meal
	 */
	public Meal(String name){
		this.setName(name);
		this.setIngredients(new HashMap<String, Ingredient>());
		this.price = 0;
		this.specialPrice = 0;
		this.promotion = false;
	}
	
	/**
	 * Serialize the meal in a string
	 */
	public String toString(){
		return name+" : "+price+"€"+(promotion ? specialPrice+"€" : "");
	}
	
	/**
	 * Get the hashcode associated to the meal
	 */
	@Override
	public int hashCode(){
		return this.name.hashCode();
	}
	
	/**
	 * Test if a given object (Meal) is the same as this meal
	 * @param meal an object (meal)
	 * @return true if the given object is the same as this meal, false otherwise
	 */
	@Override
	public boolean equals(Object meal){
		if(meal instanceof Meal)
			return this.name.equals(((Meal) meal).getName());
		if(meal instanceof String)
			return this.name.equals((String) meal);
		return false;
	}
	
	/**
	 * Get ingredients of this meal
	 * @return the list of Ingredients of this meal
	 */
	public HashMap<String, Ingredient> getIngredients() {
		return ingredients;
	}
	
	/**
	 * Get the quantity of an ingredient
	 * @param i the ingredient
	 * @return the quantity of this ingredient
	 */
	public Integer getIngredientQuantity(Ingredient i){
		return getIngredientQuantity(i.getName());
	}
	
	/**
	 * Get the quantity of an ingredient
	 * @param ingredientName the name of the ingredient
	 * @return the quantity of the ingredient having this name
	 */
	public Integer getIngredientQuantity(String ingredientName){
		return ingredients.get(ingredientName)==null ? 0 : ingredients.get(ingredientName).getQuantity();
	}
	/**
	 * Set the ingredients of this meal
	 * @param ingredients the ingredients to associate to this meal
	 */
	public void setIngredients(HashMap<String, Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	/**
	 * Add an ingredient to this meal
	 * @param ingredient the ingredient to add to this meal
	 * @param quantity the quantity associated to this ingredient
	 */
	public void putIngredient(Ingredient ingredient, Integer quantity){
		ingredient.setQuantity(quantity);
		this.ingredients.put(ingredient.getName(), ingredient);
	}
	/**
	 * Remove an ingredient from this meal
	 * @param ingredient the ingredient to remove from this meal
	 */
	public void removeIngredient(Ingredient ingredient){
		this.ingredients.remove(ingredient.getName());
	}

	/**
	 * Get the name of the meal
	 * @return the name of the meal
	 */
	public String getName() {
		return name;
	}
	/**
	 * Set the name of the meal
	 * @param name the name of the meal
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get the price of the meal
	 * @return the price of the meal
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * Set the price of the meal
	 * @param price the price of the meal
	 */
	public void setPrice(float price) {
		this.price = price;
	}
	
	/**
	 * Get the extra price of the meal, associated to 
	 * @return the extra price of the meal
	 */
	public float getExtraPrice(){
		float extraPrice = 0;
		for(Ingredient i : ingredients.values()){
			// We don't diminish the price if the customer decided to remove some of the ingredients
			if(i.getPersonalization()>0)
				extraPrice+=i.getCostPerUnit()*i.getPersonalization();
		}
		return extraPrice;
	}
	
	/**
	 * Get the special price of the meal if a promotion currently exists
	 * @return the special price of the meal if a promotion currently exists
	 */
	public float getSpecialPrice() {
		return specialPrice;
	}
	
	/**
	 * Set the special price of the meal if a promotion currently exists
	 * @param specialPrice the special price of the meal if a promotion currently exists
	 */
	public void setSpecialPrice(float specialPrice) {
		this.specialPrice = specialPrice;
	}
	/**
	 * Tells if a promotion currently exists for this meal
	 * @return true if a promotion currently exists for this meal, false otherwise
	 */
	public boolean isPromotion() {
		return promotion;
	}
	
	/**
	 * Activate or deactivate a promotion for this meal
	 * @param promotion true to activate a promotion, false to deactivate
	 */
	public void setPromotion(boolean promotion) {
		this.promotion = promotion;
	}
	
	/**
	 * Tells if this meal has been personalized or not
	 * @return true if this meal has been personalized, false otherwise
	 */
	public boolean isPersonalized(){
		// The personalization of a meal is the personalization of one of the ingredient
		for(Ingredient i : ingredients.values()){
			if(i.getPersonalization()!=null && !i.getPersonalization().equals(0))
				return true;
		}
		return false;
	}
	
	/**
	 * Personalize an ingredient of this meal
	 * @param ingredientName the name of the ingredient to personalize
	 * @param personalization the personalization to associate to this ingredient
	 */
	public void personalize(String ingredientName, Integer personalization){
		Ingredient ingredient = this.ingredients.get(ingredientName);
		if(ingredient != null){
			ingredient.setPersonalization(personalization);
		} else {
			throw new IllegalArgumentException("Ingredient not found.");
		}
	}
}
