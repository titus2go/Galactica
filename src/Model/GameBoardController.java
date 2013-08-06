package Model;

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
	/**
	 * All bullets that are fired
	 */
	private List<Bullet> fired_bullets = new ArrayList<Bullet>();
	/**
	 * List of enemy planes
	 */
	private EnemyPlane[] enemies = new EnemyPlane[4];

	private Point playerLocation;

	private int myScale = 10;
	private int bulletSpeed = 10;

	private int player_x_coordinate;

	private int player_y_coordinate;

	private int my_plane_size;

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
	private EnemyPlane enemy_test;

	private int my_width;

	private int my_height;

	private int direction;

	/**
	 * Instantiates GameBoardController.
	 * @param planeSize 
	 */
	public GameBoardController(int the_width, int the_height, int plane_size)
	{
		player_x_coordinate = the_width/4;
		player_y_coordinate = the_height -100;
		my_plane_size = plane_size;
		my_width = the_width/2;
		my_height = the_height;
		   
		initialize();
	}

	public void initialize()
	{
		//Construct a new player plane
		myPlayer = new PlayerPlane(player_x_coordinate, player_y_coordinate);
		enemy_test = new EnemyPlane(my_plane_size, player_y_coordinate/3, this);
		//Construct enemy planes
		//enemyPlane();
		
		

		//Place the plane on the board
		playerLocation = myPlayer.getCurrentLocation();
		System.out.println(myPlayer.toString());
		//my_game_board[playerLocation.getX()][playerLocation.getY()] = 1;
	}

	/**
	 * The current location of the player plane.
	 * @return playerLocation.
	 */
	public Point playerLocation()
	{
		playerLocation = myPlayer.getCurrentLocation();

		//my_game_board[playerLocation.getX()][playerLocation.getY()] = 1;
		return playerLocation;
	}
	public Point[] enemyLocation()
	{
		Point [] location = new Point[4];
		for(int i = 0; i < enemies.length; i++)
		{
			location[i] = enemies[i].getCurrentLocation();
			System.out.println("enemy" + i + "moved to" + enemies[i].getCurrentLocation().getX() +", "+ 
					enemies[i].getCurrentLocation().getY());
		}
		return location;
	}

	private void removePlayerOldLocation()
	{
		my_game_board[playerLocation.getX()][playerLocation.getY()] = 0;
	}

	/**
	 * Check if the plane is in grid
	 * @param the_x_coord The x coordinate of the plane
	 * @param the_y_coord the y coordinate of the plane
	 * @return true if the player plane is in grid or false otherwise.
	 */
	public boolean isInGrid(final int the_x_coord, final int the_y_coord)
	{
		boolean is_in_grid = true;
		if(the_x_coord < 0 || the_x_coord >= my_width)
		{
			is_in_grid = false;
		}
		else if (the_y_coord < 0 || the_y_coord >= my_height)
		{
			is_in_grid = false;
		}
		return is_in_grid;
	}

	/**
	 * Player move left
	 * reu
	 */
	public boolean playerMoveLeft()
	{
		boolean left_moved = false;
		myPlayer.moveLeft();
		
		if(isInGrid(myPlayer.getCurrentLocation().getX(), myPlayer.getCurrentLocation().getY()))
		{
			left_moved = true;
		}
		else
		{
			myPlayer.moveRight();
			left_moved = false;
		}
		return left_moved;
	}

	/**
	 * move the player plane to the right if movement is possible.
	 * @return True if the plane moved, or false if the plane didn't move. 
	 */
	public boolean playerMoveRight()
	{
		boolean right_moved = false;
		myPlayer.moveRight();
		
		if(isInGrid(myPlayer.getCurrentLocation().getX(), myPlayer.getCurrentLocation().getY()))
		{		
			right_moved = true;
			
		}
		else
		{
			myPlayer.moveLeft();
			right_moved = false;
		}
		return right_moved;
	}

	public boolean enemyMoveRight()
	{
		boolean right_moved = true;
		enemy_test.moveRight();
		if(!isInGrid(enemy_test.getCurrentLocation().getY(), enemy_test.getCurrentLocation().getX()))
		{
			enemy_test.moveLeft();
			right_moved = false;
		}
		return right_moved;
	}

	public boolean enemyMoveLeft()
	{
		boolean left_moved = true;
		enemy_test.moveLeft();
		if(!isInGrid(enemy_test.getCurrentLocation().getY(), enemy_test.getCurrentLocation().getX()))
		{
			enemy_test.moveRight();
			left_moved = false;
		}
		return left_moved;
	}
	
	public void shoot()
	{
		fired_bullets.add(new Bullet(new Point(myPlayer.getCurrentLocation().getX() + my_plane_size /2, 
				myPlayer.getCurrentLocation().getY()), 1, BulletType.PLAYER_BULLET));
		
	}
	public void moveBullet()
	{
		
		for(int i = 0; i < fired_bullets.size(); i++)
		{	
			if(isInGrid(fired_bullets.get(i).my_x, fired_bullets.get(i).my_y))
			{
				fired_bullets.get(i).shoot(bulletSpeed);
			}
			else
			{
				fired_bullets.remove(i);
			}
			
		}
		
	}
	public List<Bullet> firedBullets()
	{		
		
		return fired_bullets;
	}

	
	/**
	 * Generate enemy planes
	 */
	public EnemyPlane[] enemyPlane()
	{
		for(int i = 0; i < enemies.length; i++)
		{
			enemies[i] = new EnemyPlane(my_plane_size * i, 2 * my_plane_size, this);
		}

		return enemies;
	}
	public EnemyPlane getEnemy()
	{
		return enemy_test;
	}
	/**
	 * move enemy planes randomly
	 */
	public void moveEnemyPlanes()
	{
//		for(int i = 0; i < enemies.length; i++)
//		{
//			if(isInGrid(enemies[i].getCurrentLocation().getX(), enemies[i].getCurrentLocation().getY()/3))
//			{
//				enemies[i].moveRandomly();
//				
//			}
//		}
		//if(isInGrid(enemy_test.getCurrentLocation().getX(), enemy_test.getCurrentLocation().getY()))
		System.out.println("enemy" + enemy_test.getCurrentLocation().getX()+ "my_width "+ my_width);
		//enemy_test.moveRandomly();
		if(enemyMoveRight())
		{
			enemyMoveRight();//move right
		}
		else 
		{
			enemyMoveLeft();
		}
		
		
	
	}




}
