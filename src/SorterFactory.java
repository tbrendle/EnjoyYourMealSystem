/**
 * Factory to get a sorting strategy
 */
public class SorterFactory {
	/**
	 * Getting a sorting strategy from its name
	 * @param s name of the sorting strategy
	 * @return the sorting strategy
	 */
	public static SortingOrdersStrategy create(String s) {
		if(s.toUpperCase() == "AS IT IS")
			return new AsItIsSorter();
		else if (s.toUpperCase() == "JUST ON SALE")
			return new JustOnSaleSorter();
		else if (s.toUpperCase() == "MOSTLY MODIFIED")
			return new MostlyModifiedSorter();
		return null;
	}
}
