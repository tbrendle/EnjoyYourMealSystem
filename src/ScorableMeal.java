/**
 * Class extending Meal to be able to associate a score to meals
 */
public class ScorableMeal extends Meal{
	
	private Number score;
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
	public void setScore(Number s) {
		score=s;
	}
	
	/**
	 * Get the score of the ScorableMeal
	 * @return the score of the ScorableMeal
	 */
	public Number getScore() {
		return score;
	}

}
