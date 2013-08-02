
public class EnemyPlane extends AbstractGalacticaPlane{
	
	/**
	 * myTotalHealth is the default value of the planes total health.
	 */
	private static int myTotalHealth = 500;
	
	/**
	 * myTotalLives is the default value of the planes total life count.
	 */
	private static int myTotalLives = 5;
	
	/**
	 * myFirePower is the default value of the planes firepower
	 */
	private static int myFirePower = 3;
	
	
	private static final int DEFAULT_X_COORDINATE = 10;
	private static final int DEFAULT_Y_COORDINATE = 2;
	
	private static final int DEFAULT_DIRECTION = 1;
	
	
	/**
	 * A constructor that instantiates the enemy plane.
	 */
	public EnemyPlane(final int the_scale)
	{
		super(myTotalHealth, 
			  myTotalLives, 
			  myFirePower, 
			  new Point(DEFAULT_X_COORDINATE, DEFAULT_Y_COORDINATE), the_scale, DEFAULT_DIRECTION);
	}
}
