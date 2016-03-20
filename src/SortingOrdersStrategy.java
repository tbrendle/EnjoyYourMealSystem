import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
/**
 * Abstract class for sorting strategies in general
 */
public abstract class SortingOrdersStrategy {
	/**
	 * Associating scores to meals and getting the ordered list from there using getListfromHashMap
	 * @param o the order containing meals
	 * @return meals ordered
	 */
	public abstract ArrayList<ScorableMeal> sort(ArrayList<Order> o);
	/**
	 * Getting a ordered list from a hashmap associating elements to scores
	 * @param d the hashmap associating numbers (scores) to meals
	 * @return the list of values in the hashmap ordered by there scores
	 */
	public ArrayList<ScorableMeal> getListfromHashMap(HashMap<Meal, Number> d) {
		ArrayList<ScorableMeal> sList = new ArrayList<ScorableMeal>();
		for(Meal m : d.keySet()){
			ScorableMeal sm = new ScorableMeal(m.getName());
			sm.setScore(d.get(m));
			sList.add(sm);
		}
		Collections.sort(sList, new Comparator<ScorableMeal>() {
	        @Override public int compare(ScorableMeal s1, ScorableMeal s2) {
	            return (s1.getScore().doubleValue() > s2.getScore().doubleValue() ? 1 : 0);
	        }
	    });
		return sList;
    }
}
