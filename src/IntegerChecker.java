
public class IntegerChecker implements InputChecker {
	private Integer min;
	private Integer max;
	public IntegerChecker(Integer min, Integer max){
		this.min = min;
		this.max = max;
	}
	@Override
	public boolean check(String str) {
		try{
			Integer value = Integer.parseInt(str);
			return (min==null || value>=min) && (max ==null || value <= max);
		} catch (NumberFormatException e) {
			return false; 
		}
	}

}
