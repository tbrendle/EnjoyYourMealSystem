
public class MealChecker implements InputChecker {
	private Restaurant restaurant;
	private boolean emptyAllowed;
	public MealChecker(Restaurant r, boolean emptyAllowed){
		this.restaurant = r;
		this.emptyAllowed = emptyAllowed;
	}
	@Override
	public boolean check(String str) {
		return restaurant.getMeal(str)!=null || (emptyAllowed && str.equals(""));
	}

}
