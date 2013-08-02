import java.util.ArrayList;
import java.util.List;


public class GameBoardController {
	
	/**
	 * my_game_board is a 2D array representing the coordinates on the board
	 */
	private int[][] my_game_board;
	
	/**
	 * myPlayer represents the current player.
	 */
	private PlayerPlane myPlayer;
	
	private Point playerLocation;
	
	private int myScale = 1;
	
	/**
	 * An constant empty space variable
	 */
	private static final String EMPTY_SPACE = "0";
	
	/**
	 * A new line string character.
	 */
	private static final String NEW_LINE = "\n";
	
	
	/**
	 * BULLET is the string representation of one bullet
	 */
	private static final String BULLET = "*";
	
	/**
	 * PLANE_REP is the string representation of the player plane.
	 */
	private static final String PLANE_REP = "S";
	
	private static final int BOARD_HEIGHT = 20;
	
	private static final int BOARD_WIDTH = 15;
	
	/**
	 * Instantiates GameBoardController.
	 */
	public GameBoardController()
	{
		my_game_board = new int[BOARD_HEIGHT][BOARD_WIDTH];
	}
	
	public void start()
	{
		//Construct a new player plane
		myPlayer = new PlayerPlane(myScale);
		
		//Place the plane on the board
		playerLocation = myPlayer.getCurrentLocation();
		System.out.println(myPlayer.toString());
		my_game_board[playerLocation.getX()][playerLocation.getY()] = 1;
	}
	
	private void updatePlayerLocation()
	{
		playerLocation = myPlayer.getCurrentLocation();
		
		my_game_board[playerLocation.getX()][playerLocation.getY()] = 1;
	}
	
	private void removePlayerOldLocation()
	{
		my_game_board[playerLocation.getX()][playerLocation.getY()] = 0;
	}
	
	private boolean failedBoundaryCheck(final int the_x_coord, final int the_y_coord)
	{
		boolean will_crash_wall = false;
		if(the_x_coord < 0 || the_x_coord >= BOARD_WIDTH)
		{
			will_crash_wall = true;
		}
		else if (the_y_coord < 0 || the_y_coord >= BOARD_HEIGHT)
		{
			will_crash_wall = true;
		}
		return will_crash_wall;
	}
	
	/**
	 * Player move left
	 * Note:  Remember to change plane rep on the board, also remember to check boundaries
	 */
	public void playerMoveLeft()
	{
	//	System.out.println("Player x: " + myPlayer.getCurrentLocation().getY() + "Player y: " +  myPlayer.getCurrentLocation().getX());
		if(!failedBoundaryCheck(myPlayer.getCurrentLocation().getY() - myScale, myPlayer.getCurrentLocation().getX()))
		{
		//	System.out.println("Player old location: " + myPlayer.toString());
			removePlayerOldLocation();
			myPlayer.moveLeft();
			updatePlayerLocation();
		//	System.out.println("Player new location: " + myPlayer.toString());
		}
	}
	
	public void playerMoveRight()
	{
		System.out.println("Player x: " + myPlayer.getCurrentLocation().getY() + "Player y: " +  myPlayer.getCurrentLocation().getX());
		if(!failedBoundaryCheck(myPlayer.getCurrentLocation().getY() + myScale, myPlayer.getCurrentLocation().getX()))
		{
		//	System.out.println("Player old location: " + myPlayer.toString());
			removePlayerOldLocation();
			myPlayer.moveRight();
			updatePlayerLocation();
		//	System.out.println("Player new location: " + myPlayer.toString());
		}
	}
	
	public void playerMoveUp()
	{
		if(!failedBoundaryCheck(myPlayer.getCurrentLocation().getY(), myPlayer.getCurrentLocation().getX() - myScale))
		{
		//	System.out.println("Player old location: " + myPlayer.toString());
			removePlayerOldLocation();
			myPlayer.moveUp();
			updatePlayerLocation();
		//	System.out.println("Player new location: " + myPlayer.toString());
		}
	}
	
	public void playerMoveDown()
	{
		if(!failedBoundaryCheck(myPlayer.getCurrentLocation().getY() + myScale, myPlayer.getCurrentLocation().getX() + myScale))
		{
		//	System.out.println("Player old location: " + myPlayer.toString());
			removePlayerOldLocation();
			myPlayer.moveDown();
			updatePlayerLocation();
		//	System.out.println("Player new location: " + myPlayer.toString());
		}
	}
	
	public void playerShoot()
	{
		myPlayer.shoot();
	}
	
	public void updatePlayerBulletLocation()
	{
//		//Remove old bullet path
		List<Point> bullets_location = myPlayer.getBulletsCoordinates();
		for(Point this_bullet_location : bullets_location)
		{
			if(!this_bullet_location.isEqual(myPlayer.getCurrentLocation()))
			{
				my_game_board[this_bullet_location.getX()][this_bullet_location.getY()] = 0;
			}
		}
		
		//Build new bullet path
		myPlayer.updateBulletsCoordinates();
		bullets_location = myPlayer.getBulletsCoordinates();
		for(int i = 0; i < bullets_location.size(); i++)
		{
			Point this_bullet_location = bullets_location.get(i);
			if(!failedBoundaryCheck(this_bullet_location.getY(), this_bullet_location.getX()))
			{
				my_game_board[this_bullet_location.getX()][this_bullet_location.getY()] = 2;
			}
			else {
				myPlayer.removeBulletFromPath(i);
			}
		}
	}
	
	public String toString()
	{
		StringBuilder theStringBuilder = new StringBuilder();	//Constructs the left side wall
		for(int y = 0; y < my_game_board.length; y++)
		{	
			theStringBuilder.append("|");
			for(int x = 0; x < my_game_board[y].length; x++)
			{
				if(my_game_board[y][x] == 0)
				{
					theStringBuilder.append(EMPTY_SPACE);
				}
				else if(my_game_board[y][x] == 1)
				{
					theStringBuilder.append(PLANE_REP);
				}
				else if(my_game_board[y][x] == 2)
				{
					theStringBuilder.append(BULLET);
				}
			}
			theStringBuilder.append("|");  //Constructs the right side wall
			theStringBuilder.append(NEW_LINE);
		}
		return theStringBuilder.toString();
	}
	
	

}
