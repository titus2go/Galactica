
public class PlayerPlane extends AbstractGalacticaPlane {
	
	private static int myTotalHealth = 1000;
	
	private static int myTotalLives = 5;
	
	private static int myFirePower = 10;
	
//	private static Point myCurrentLocation;
	
	private static int DEFAULT_Y_COORDINATE = 19;
	private static int DEFAULT_X_COORDINATE = 8;
	
	private static final int DEFAULT_DIRECTION = -1;
	
	private int myCurrentXCoord;
	private int myCurrentYCoord;
	
	
	public PlayerPlane(final int scale)
	{
		super(myTotalHealth, 
			  myTotalLives, 
			  myFirePower, 
			  new Point(DEFAULT_Y_COORDINATE, DEFAULT_X_COORDINATE), scale, DEFAULT_DIRECTION);
		myCurrentXCoord = DEFAULT_X_COORDINATE;
		myCurrentYCoord = DEFAULT_Y_COORDINATE;
	}
	
	/**
	 * @override 
	 */
	public void moveLeft()
	{
		myCurrentXCoord -= super.getScale();
		super.setPlaneCoordinate(myCurrentYCoord, myCurrentXCoord);
	}
	
	public void moveRight()
	{
		myCurrentXCoord += super.getScale();
		super.setPlaneCoordinate(myCurrentYCoord, myCurrentXCoord);
	}
	
	public void moveDown()
	{
		myCurrentYCoord += super.getScale();
		super.setPlaneCoordinate(myCurrentYCoord, myCurrentXCoord);
	}
	
	public void moveUp()
	{
		myCurrentYCoord -= super.getScale();
		super.setPlaneCoordinate(myCurrentYCoord, myCurrentXCoord);
	}
	

}
