/*public class IngredientChecker implements InputChecker {
	private Meal meal;
	private boolean emptyAllowed;
	public IngredientChecker(Meal m, boolean emptyAllowed){
		this.meal = m;
		this.emptyAllowed = emptyAllowed;
	}
	@Override
	public boolean check(String str) {
		return meal.getIngredientQuantity(new Ingredient(str))>0 || (emptyAllowed && str.equals(""));
	}
}*/
