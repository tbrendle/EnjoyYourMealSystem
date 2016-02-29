
public class SorterFactory {
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
