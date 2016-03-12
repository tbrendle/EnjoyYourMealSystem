import java.util.ArrayList;
import java.util.HashMap;

public class JustOnSaleSorter extends SortingOrdersStrategy{

	@Override
	public ArrayList<Scorable> sort(ArrayList<Order> oList) {
		HashMap<Meal, Number> d = new HashMap<Meal, Number>();
		for(Order o : oList){
			for(Meal m : o.getMeals()){
				if(m.isPromotion())//TODO ? && o.getCustomer().getFidelityCard())
					if(d.get(m)==null)
						d.put(m, 1);
					else
						d.put(m, d.get(m).intValue()+1);
			}
		}
		return this.getListfromHashMap(d);
	}

}

