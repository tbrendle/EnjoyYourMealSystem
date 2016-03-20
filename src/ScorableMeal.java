import java.util.HashMap;

/**
 * Class extending Meal to be able to associate a score to meals
 */
public class ScorableMeal extends Meal implements Comparable{
	
	private Integer score;
	/**
	 * ScorableMeal constructor
	 * @param name the name of the ScorableMeal
	 */
	public ScorableMeal(String name) {
		super(name);
	}

	/**
	 * Set the score of the ScorableMeal
	 * @param s the score of the ScorableMeal
	 */
	public void setScore(Integer s) {
		score=s;
	}
	
	/**
	 * Get the score of the ScorableMeal
	 * @return the score of the ScorableMeal
	 */
	public Integer getScore() {
		return score;
	}

	
	@Override
	public int compareTo(Object o) {
		if(o instanceof ScorableMeal)
			return ((ScorableMeal) o).getScore()-score;
		else
			return 0;
	}

}
