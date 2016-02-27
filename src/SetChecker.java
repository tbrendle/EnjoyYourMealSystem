import java.util.Set;

public class SetChecker<T> implements InputChecker {
	private Set<T> set;
	private boolean emptyAllowed;
	public SetChecker(Set<T> set, boolean emptyAllowed){
		this.set = set;
		this.emptyAllowed = emptyAllowed;
	}
	@Override
	public boolean check(String str) {
		return set.contains(str) || (emptyAllowed && str.equals(""));
	}

}
