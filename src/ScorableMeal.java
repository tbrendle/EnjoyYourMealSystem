
public class ScorableMeal extends Meal implements Scorable{
	
	private Number score;
	public ScorableMeal(String name) {
		super(name);
	}

	@Override
	public void setScore(Number s) {
		score=s;
	}

	@Override
	public Number getScore() {
		return score;
	}

}
