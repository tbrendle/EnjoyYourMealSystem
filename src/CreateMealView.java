
public class CreateMealView extends AbstractView {
	public CreateMealView(Program p) {
		super(p);
	}

	@Override
	public void show() {
		Meal m = program.currentMeal();
		System.out.println("You are editing a meal");
		System.out.println("Name :" + m.getName());
		System.out.println("Price :" + m.getPrice());
		System.out.println("Promotion price :" + m.getSpecialPrice());
		for(Ingredient i : m.getIngredients().values()){
			System.out.println("Name : "+i.getName()+" --- Quantity: "+i.getQuantity());
		}
		System.out.println("addIngredient<Name, Quantity in grams>");
		System.out.println("saveMeal: to save meal");
	}
	
	@Override
	public void show(Object o){
		if(o instanceof Meal){
			System.out.println(((Meal) o).getName()+" successfully saved");
		} else 
			show();
	}

}
