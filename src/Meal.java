import java.util.HashMap;

public class Meal {
	private String name;
	private HashMap<Ingredient, Integer> ingredients;
	private float price;
	private float specialPrice;
	private boolean promotion;
	
	public Meal(String name){
		this.setName(name);
		this.setIngredients(new HashMap<Ingredient, Integer>());
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
	
	public HashMap<Ingredient, Integer> getIngredients() {
		return ingredients;
	}
	
	public Integer getIngredientQuantity(Ingredient i){
		return (ingredients.get(i)==null ? 0 : ingredients.get(i)) ;
	}

	public void setIngredients(HashMap<Ingredient, Integer> ingredients) {
		this.ingredients = ingredients;
	}
	
	public void putIngredient(Ingredient ingredient, Integer quantity){
		this.ingredients.put(ingredient, quantity);
	}
	
	public void removeIngredient(Ingredient ingredient){
		this.ingredients.remove(ingredient);
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
	
	public void personalize(String ingredientName, String personalization){
		Ingredient ingredient = new Ingredient(ingredientName, personalization);
		Integer n = this.getIngredientQuantity(ingredient);
		if(n>0){
			this.ingredients.remove(ingredient);
			this.ingredients.put(ingredient, n);
		}
	}
}
