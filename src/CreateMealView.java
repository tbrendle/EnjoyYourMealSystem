
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
	}

}
