import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * One meal sorting strategy : the meals ordered just when there is a special offer
 *
 */
public class JustOnSaleSorter extends SortingOrdersStrategy{
	
	/**
	 * Sorting meals by the most ordered, as they are
	 * @param oList List of Orders
	 * @param meals List of reference meal
	 * @return ArrayList of meals, ordered by the most ordered (just when there is a special offer)
	 */
	@Override
	public ArrayList<ScorableMeal> sort(ArrayList<Order> oList, HashMap<String, Meal> meals) {
		HashMap<String, Integer> d = new HashMap<String, Integer>();
		for(Meal m : meals.values()){
			d.put(m.getName(), 0);
		}
		for(Order o : oList){
			for(Meal m : o.getMeals()){
				//We check only if the meal was ordered by a customer who cared about the promotion
				if(m.isPromotion() && o.getCustomer().getFidelityCard() instanceof BasicCard)
					//We do not show removed meals
					if(d.get(m.getName())!=null)
						d.put(m.getName(), d.get(m.getName())+1);
			}
		}
		return this.getListfromHashMap(d);
	}

}

