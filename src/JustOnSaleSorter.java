import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * One meal sorting strategy : the meals ordered just when there is a special offer
 *
 */
public class JustOnSaleSorter extends SortingOrdersStrategy{
	
	/**
	 * Sorting meals by the most ordered, just when there is a special offer
	 * @param oList List of Meals
	 * @return ArrayList of meals, ordered by the most ordered (just when there is a special offer)
	 */
	@Override
	public ArrayList<ScorableMeal> sort(ArrayList<Order> oList) {
		HashMap<Meal, Number> d = new HashMap<Meal, Number>();
		for(Order o : oList){
			for(Meal m : o.getMeals()){
				//We check only if the meal was ordered by a customer who cared about the promotion
				//TODO : check if the card taken in account is the current of or the one when to meal was taken
				if(m.isPromotion() && o.getCustomer().getFidelityCard() instanceof BasicCard)
					if(d.get(m)==null)
						d.put(m, 1);
					else
						d.put(m, d.get(m).intValue()+1);
			}
		}
		return this.getListfromHashMap(d);
	}

}

