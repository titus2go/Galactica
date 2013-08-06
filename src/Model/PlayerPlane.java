package Model;

import java.util.ArrayList;
import java.util.List;

public class PlayerPlane extends AbstractGalacticaPlane {

	private static int myTotalHealth = 1000;

	private static int myTotalLives = 5;

	private static int myFirePower = 10;

//	private static Point myCurrentLocation;

	
	private static final int DEFAULT_DIRECTION = -1;

	private int myCurrentXCoord;
	private int myCurrentYCoord;
	
	public PlayerPlane(int x, int y)
	{
		super(myTotalHealth, 
			  myTotalLives, 
			  myFirePower, 
			  new Point(x, y),  DEFAULT_DIRECTION);
			myCurrentXCoord = x;
			myCurrentYCoord = y;
	}

	/**
	 * @override 
	 */
	public void moveLeft()
	{
		myCurrentXCoord -= super.getScale();
		super.setPlaneCoordinate(myCurrentXCoord, myCurrentYCoord);
	}

	public void moveRight()
	{
		myCurrentXCoord += super.getScale();
		super.setPlaneCoordinate(myCurrentXCoord, myCurrentYCoord);
	}

	public void moveDown()
	{
		myCurrentYCoord += super.getScale();
		super.setPlaneCoordinate(myCurrentXCoord, myCurrentYCoord);
	}

	public void moveUp()
	{
		myCurrentYCoord -= super.getScale();
		super.setPlaneCoordinate(myCurrentXCoord, myCurrentYCoord);
	}
	
	
	
	


}
