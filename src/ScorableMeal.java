/**
 * Class extending Meal to be able to associate a score to meals
 */
public class ScorableMeal extends Meal implements Comparable<ScorableMeal>{
	
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

	/**
	 * Method overriding the compareTo of Comparable (that it implements)
	 * @param o object to compare this instance to
	 * @return 0 if the Object is not a ScorableMeal, of the difference between the score of the object given to the score of this instance
	 */
	@Override
	public int compareTo(ScorableMeal o) {
		return o.getScore()-score;
	}


}
