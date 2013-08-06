package Model;

import java.util.Random;

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

	private int myCurrentXCoord;

	private int myCurrentYCoord;

	private GameBoardController my_board;


	/**
	 * A constructor that instantiates the enemy plane.
	 */
	public EnemyPlane(int x, int y, GameBoardController the_board)
	{
		super(myTotalHealth, 
			  myTotalLives, 
			  myFirePower, 
			  new Point(x, y), DEFAULT_DIRECTION);
		myCurrentXCoord = x;
		myCurrentYCoord = y;
		my_board = the_board;
	}

	/**
	 * @override 
	 */
	public void moveLeft()
	{
		myCurrentXCoord -= super.getScale();
		super.setPlaneCoordinate(myCurrentXCoord, myCurrentYCoord);
		
		
	}
	public void move(int direction)
	{
		myCurrentXCoord += super.getScale()*direction;
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
		if(!my_board.isInGrid(myCurrentXCoord, myCurrentYCoord/2))
		{
			myCurrentYCoord -= super.getScale();
			super.setPlaneCoordinate(myCurrentXCoord, myCurrentYCoord);		
		}
	}

	public void moveUp()
	{
		myCurrentYCoord -= super.getScale();
		super.setPlaneCoordinate(myCurrentXCoord, myCurrentYCoord);
		if(!my_board.isInGrid(myCurrentXCoord, myCurrentYCoord/2))
		{
			myCurrentYCoord += super.getScale();
			super.setPlaneCoordinate(myCurrentXCoord, myCurrentYCoord);		
		}
	}
	
	public void moveRandomly()
	{
		Random rand = new Random();
		int rand_direction = rand.nextInt(4);
		switch(rand_direction)
		{
		case 0: moveUp();
			break;
		case 1: moveDown();
			break;
		case 2: moveLeft();
			break;
		case 3: moveRight();
			break;
		
		}
	}
	
	
	
}
