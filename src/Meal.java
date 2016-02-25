import java.util.HashMap;

public class Meal {
	private String name;
	private HashMap<String, String> ingredients;
	
	public Meal(String name){
		this.setName(name);
		this.setIngredients(new HashMap<String, String>());
	}
	
	@Override
	public int hashCode(){
		return this.name.hashCode();
	}
	
	@Override
	public boolean equals(Object meal){
		if(meal instanceof Meal)
			return this.name.equals(((Meal) meal).getName());
		return false;
	}
	
	public HashMap<String, String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(HashMap<String, String> ingredients) {
		this.ingredients = ingredients;
	}
	
	public void putIngredient(String ingredient, String quantity){
		this.ingredients.put(ingredient, quantity);
	}
	
	public void removeIngredient(String ingredient){
		this.ingredients.remove(ingredient);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
		return name+":"+ingredients.toString();
	}
}
