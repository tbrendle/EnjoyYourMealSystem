import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public interface SortingOrdersStrategy {
	public ArrayList<Scorable> sort(ArrayList<Order> o);
	default ArrayList<Scorable> getListfromHashMap(HashMap<Meal, Number> d) {
		ArrayList<Scorable> sList = new ArrayList<Scorable>();
		for(Meal m : d.keySet()){
			ScorableMeal sm = (ScorableMeal) m;
			sm.setScore(d.get(m));
			sList.add(sm);
		}
		Collections.sort(sList, new Comparator<Scorable>() {
	        @Override public int compare(Scorable s1, Scorable s2) {
	            return (s1.getScore().doubleValue() > s2.getScore().doubleValue() ? 1 : 0);
	        }
	    });
		return sList;
    }
}
