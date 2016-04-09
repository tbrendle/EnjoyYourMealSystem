package core;
/**
 * Factory to get a sorting strategy
 */
public class SorterFactory {
	/**
	 * Getting a sorting strategy from its name
	 * @param s name of the sorting strategy
	 * @return the sorting strategy
	 * @throws IllegalArgumentException if the sorting criteria is not implemented yet
	 */
	public static SortingOrdersStrategy create(String s) throws IllegalArgumentException{
		if(s.toUpperCase().equals("AS IT IS"))
			return new AsItIsSorter();
		else if (s.toUpperCase().equals("JUST ON SALE"))
			return new JustOnSaleSorter();
		else if (s.toUpperCase().equals("MOSTLY MODIFIED"))
			return new MostlyModifiedSorter();
		else
			throw new IllegalArgumentException("Can not sort this way : " + s.toUpperCase() + " not yet implemented");
	}
}
