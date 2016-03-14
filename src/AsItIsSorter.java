import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * One meal sorting strategy : the meals ordered as they are
 *
 */
public class AsItIsSorter extends SortingOrdersStrategy{

	/**
	 * Sorting meals by the most ordered, as they are
	 * @param oList List of Meals
	 * @return ArrayList of meals, ordered by the most ordered (as they are)
	 */
	@Override
	public ArrayList<ScorableMeal> sort(ArrayList<Order> oList) {
		HashMap<Meal, Number> d = new HashMap<Meal, Number>();
		for(Order o : oList){
			for(Meal m : o.getMeals()){
				if(d.get(m)==null)
					d.put(m, 1);
				else
					d.put(m, d.get(m).intValue()+1);
			}
		}
		return this.getListfromHashMap(d);
	}

}
