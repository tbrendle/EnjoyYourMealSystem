import java.util.HashMap;
//DEPRECATED
public class CoreManager implements BasicManager{
	public User user;
	public Restaurant restaurant;
	public UserInterface ui;
	
	public CoreManager(UserInterface ui){
		this.ui = ui;
	}
	
	public void init(){
		//LOAD RESTAURANT HERE
		//TODO: LOAD IT FROM DB/DUMP/ETC...
		String[] meals = {"Menu1", "Menu2", "Menu3", "Menu4"};
		Ingredient[] ingredients = {new Ingredient("Ingredient1"), new Ingredient("Ingredient2"),new Ingredient("Ingredient3"),new Ingredient("Ingredient4")};
		HashMap<String, Meal> availableMeals = new HashMap<String,Meal>();
		float i = 0;
		Meal tempMeal;
		for(String m : meals){
			i++;
			tempMeal = new Meal(m);
			tempMeal.setPrice(10*i);
			tempMeal.setSpecialPrice(10*i-1/2);
			tempMeal.setPromotion(i%2==0);
			tempMeal.putIngredient(ingredients[(int) (i%4)], (int) i%4+1);
			i++;
			tempMeal.putIngredient(ingredients[(int) (i%4)], (int) i%4+1);
			i++;
			tempMeal.putIngredient(ingredients[(int) (i%4)], (int) i%4+1);
			availableMeals.put(tempMeal.getName(), tempMeal);
		}
		Restaurant res = new Restaurant();
		res.setMeals(availableMeals);
		this.restaurant = res;
	}
	
	public void authenticateUser(){
		
	}
	
	public void run(){
		
	}
	
	public static void main(String[] args) {
		CoreManager c = new CoreManager(new NativeInterface());
		c.init();
		c.run();
	}
}
