import java.util.ArrayList;
import java.util.HashMap;

public class JustOnSaleSorter extends SortingOrdersStrategy{

	@Override
	public ArrayList<Scorable> sort(ArrayList<Order> oList) {
		HashMap<Meal, Number> d = new HashMap<Meal, Number>();
		for(Order o : oList){
			for(Meal m : o.getMeals()){
				//We check only if the meal was ordered by a customer 
				//who cared about the promotion
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

