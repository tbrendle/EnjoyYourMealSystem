import java.io.Serializable;
import java.util.HashMap;

public class Meal implements Serializable{
	private String name;
	private HashMap<String, Ingredient> ingredients;
	private float price;
	private float specialPrice;
	private boolean promotion;
	
	public Meal(String name){
		this.setName(name);
		this.setIngredients(new HashMap<String, Ingredient>());
		this.price = 0;
		this.specialPrice = 0;
		this.promotion = false;
	}
	
	public String toString(){
		return name+" : "+price+"€"+(promotion ? specialPrice+"€" : "");
	}
	
	@Override
	public int hashCode(){
		return this.name.hashCode();
	}
	
	@Override
	public boolean equals(Object meal){
		if(meal instanceof Meal)
			return this.name.equals(((Meal) meal).getName());
		if(meal instanceof String)
			return this.name.equals((String) meal);
		return false;
	}
	
	public HashMap<String, Ingredient> getIngredients() {
		return ingredients;
	}
	
	public Integer getIngredientQuantity(Ingredient i){
		return getIngredientQuantity(i.getName());
	}
	
	public Integer getIngredientQuantity(String ingredientName){
		return ingredients.get(ingredientName)==null ? 0 : ingredients.get(ingredientName).getQuantity();
	}

	public void setIngredients(HashMap<String, Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	public void putIngredient(Ingredient ingredient, Integer quantity){
		ingredient.setQuantity(quantity);
		this.ingredients.put(ingredient.getName(), ingredient);
	}
	
	public void removeIngredient(Ingredient ingredient){
		this.ingredients.remove(ingredient.getName());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getSpecialPrice() {
		return specialPrice;
	}

	public void setSpecialPrice(float specialPrice) {
		this.specialPrice = specialPrice;
	}

	public boolean isPromotion() {
		return promotion;
	}

	public void setPromotion(boolean promotion) {
		this.promotion = promotion;
	}
	/*public void personalize(Ingredient ingredient){
		Integer n = this.getIngredientQuantity(ingredient);
		if(n!=null && !n.equals(0)){
			this.ingredients.put(ingredient.getName(), ingredient);
		} else {
			//TODO: THROW EXCEPTION: INGREDIENT NOT FOUND
		}
	}*/
	public boolean isPersonalized(){
		for(Ingredient i : ingredients.values()){
			if(i.getPersonalization()!=null && !i.getPersonalization().equals(0))
				return true;
		}
		return false;
	}
	
	public void personalize(String ingredientName, Integer personalization){
		
		Ingredient ingredient = this.ingredients.get(ingredientName);
		if(ingredient != null){
			ingredient.setPersonalization(personalization);
		} else {
			//TODO: THROW EXCEPTION: INGREDIENT NOT FOUND
		}
	}
}
