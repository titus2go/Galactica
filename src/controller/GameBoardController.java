package controller;
import java.util.ArrayList;
import java.util.List;

import model.Bullet;
import model.EnemyPlane;
import model.PlayerPlane;
import model.Point;


public class GameBoardController {
	
	/**
	 * my_game_board is a 2D array representing the coordinates on the board
	 */
	private int[][] my_game_board;
	
	/**
	 * myPlayer represents the current player.
	 */
	private PlayerPlane myPlayer;
	
	private EnemyPlane[] enemies = new EnemyPlane[4];
	
	private List<Bullet> fired_bullets = new ArrayList<Bullet>();
	
	/**
	 * enemyPlayers represent a list of enemy players.
	 */
	private List<EnemyPlane> myEnemyPlayers;
	
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
	private static final String PLAYER_REP = "S";
	
	private static final String ENEMY_REP = "E";
	
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
		
		//Construct enemy plane
		myEnemyPlayers = new ArrayList<EnemyPlane>();
		for(int i = 2; i < 15; i+= 2)
		{
			myEnemyPlayers.add(new EnemyPlane(myScale, 1, i));
		}
		
		
		//Place the plane on the board
		playerLocation = myPlayer.getCurrentLocation();
		System.out.println(myPlayer.toString());
		my_game_board[playerLocation.getX()][playerLocation.getY()] = 1;
		
		//Place enemy on the game board
		for(EnemyPlane enemy : myEnemyPlayers)
		{
			my_game_board[enemy.getCurrentLocation().getX()][enemy.getCurrentLocation().getY()] = 10;
		}
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
	
	public void shoot()
	{
		fired_bullets.add(new Bullet(new Point(myPlayer.getCurrentLocation().getX() + my_plane_size /2, 
				myPlayer.getCurrentLocation().getY()), 1, BulletType.PLAYER_BULLET));

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
	
	/**
	 * Check if any of the plane was hit, then update the graphics to display the newest result.
	 * @return
	 */
	public boolean planeWasHit()
	{
		boolean wasHit = false;
		List<Bullet> playerBullets = myPlayer.getBullets();
		for(EnemyPlane enemyPlane : myEnemyPlayers)
		{
			//Each enemy plane have different bullets, check each bullet and take the damage
			for(int bulletIndex = 0; bulletIndex < enemyPlane.getBullets().size(); bulletIndex++)
			{
				if(myPlayer.wasHit(enemyPlane.getBullets()))
				{
					myPlayer.takeDamage(enemyPlane.getBullets().get(bulletIndex).getDamage());
				}
			}
			
			//Each player have different bullets, check each bullets from player and take the damage to the enemy
			for(int playerBulletIndex = 0; playerBulletIndex < playerBullets.size(); playerBulletIndex++)
			{
				if(enemyPlane.wasHit(playerBullets.get(playerBulletIndex)))
				{
					enemyPlane.takeDamage(playerBullets.get(playerBulletIndex).getDamage());
				}
			}
		}
		return wasHit;
		
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
					theStringBuilder.append(PLAYER_REP);
				}
				else if(my_game_board[y][x] == 2)
				{
					theStringBuilder.append(BULLET);
				}
				else if(my_game_board[y][x] == 10)
				{
					theStringBuilder.append(ENEMY_REP);
				}
			}
			theStringBuilder.append("|");  //Constructs the right side wall
			theStringBuilder.append(NEW_LINE);
		}
		return theStringBuilder.toString();
	}
	
	

}
