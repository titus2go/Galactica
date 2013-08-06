package model;
/*
 * Developers:  Titus, Mike
 * AbstractGalacticaPlane class
 * This class is designed to be a generic class for both the player and the enemy.
 */

import java.util.ArrayList;
import java.util.List;


public class AbstractGalacticaPlane {
	/*
	 * myTotalHealth is the maximum health the plane has.
	 * This variable depends on the level of the plane.
	 */
	private int myTotalHealth;
	
	/*
	 * myTotalLives is the life count of each plane.
	 * myTotalLives will reduce when myTotalHealth is 0.
	 */
	private int myTotalLives;
	
	/*
	 * myHealthCount is the health that is left of the plane.
	 * It is reduced after taking damage.
	 */
	private int myHealthCount;
	
	/*
	 * myFirePower is essentially the damage count for each bullet
	 */
	private int myFirePower;
	
	
	/**
	 * isAlive is true only when myTotalLives > 0.
	 */
	private boolean isAlive;
	
	/**
	 * my_scale is the variable to determine how fast the bullet travels
	 */
	private int myScale = 1;
	
	
	
	/**
	 * myBullets provides the bullet information of the plane's bullets.
	 */
	private List<Bullet> myBullets;
	
	/**
	 * myCurrentCoordinate provides the coordinate of the plane's current location.
	 */
	private Point myCurrentCoordinate;
	
	/**
	 * myDirection provides the direction in which the plane is facing and shooting.
	 */
	private int myDirection;
	
	public AbstractGalacticaPlane(final int totalHealth, 
								  final int totalLives, 
								  final int firePower, 
								  Point currentCoordinate, 
								  final int scale,
								  final int direction)
	{
		myTotalHealth = totalHealth;
		myHealthCount = totalHealth;
		myFirePower = firePower;
		myTotalLives = totalLives;
		isAlive = true;
		myCurrentCoordinate = currentCoordinate;
		myScale = scale;
		myBullets = new ArrayList<Bullet>();
		myDirection = direction;
			
	}
	
	/**
	 * getScale() returns the speed of the bullet
	 * @return int myScale
	 */
	protected int getScale()
	{
		return myScale;
	}
	
	/**
	 * moveLeft() moves the plane to its left.
	 * Note: Enemy and player both have different orientation.
	 */
	public void moveLeft()
	{
		myCurrentCoordinate = new Point(myCurrentCoordinate.getX() - myScale, myCurrentCoordinate.getY());
	}
	
	/**
	 * moveRight() moves the plane to its right.
	 * Note: Enemy and player both have different orientation.
	 */
	public void moveRight()
	{
		myCurrentCoordinate = new Point(myCurrentCoordinate.getX() + myScale, myCurrentCoordinate.getY());
	}
	
	
	/**
	 * moveUp() moves the plane to its upward position
	 * Note: Enemy and player both have different orientation.
	 */
	public void moveUp()
	{
		myCurrentCoordinate = new Point(myCurrentCoordinate.getX(), myCurrentCoordinate.getY() - myScale);
	}
	
	/**
	 * moveDown() moves the plane to its downward position
	 * Note: Enemy and player both have different orientation.
	 */
	public void moveDown()
	{
		myCurrentCoordinate = new Point(myCurrentCoordinate.getX(), myCurrentCoordinate.getY() + myScale);
		
	}
	
	/**
	 * getHealthCount() returns the current health the plane has i.e 245/500 -> 245
	 * @return int myHealthCount
	 */
	public int getHealthCount()
	{
		return myHealthCount;
	}
	
	/**
	 * Allow children to access and set the plane's coordinate
	 */
	protected void setPlaneCoordinate(final int new_x_coord, final int new_y_coord)
	{
		myCurrentCoordinate = new Point(new_x_coord, new_y_coord);
	}
	
	
	/**
	 * takeDamge() allows the plane to take a certain amount of damage from enemyBullets.
	 * @param damageCount
	 */
	public void takeDamage(int damageCount)
	{
		if(myHealthCount == damageCount || myHealthCount < damageCount)
		{
			myHealthCount = 0;
			if(myTotalLives > 0)
			{
				myTotalLives--;
			}
			else {
				isAlive = false;
			}
		}
		else if(myHealthCount > damageCount)
		{
			myHealthCount -= damageCount;
		}
	}
	
	/**
	 * Check whether the plane was hit with a list of enemy bullets
	 * @param other_coordinate
	 * @return boolean value
	 */
	public boolean wasHit(List<Bullet> enemyBullets)
	{	
		boolean plane_was_hit = false;
		for(Bullet enemyBullet : enemyBullets)
		{
			if(myCurrentCoordinate.getX() == enemyBullet.getCoordinate().getX() 
					&& myCurrentCoordinate.getY() == enemyBullet.getCoordinate().getY())
			{
				plane_was_hit = true;
			}
		}
		return plane_was_hit;
	}
	
	/**
	 * Check whether the plane was hit with an enemy bullet.
	 * @param enemy_bullet
	 * @return boolean plane_was_hit
	 */
	public boolean wasHit(Bullet enemy_bullet)
	{
		boolean plane_was_hit = false;
		Point enemy_bullet_coordinate = enemy_bullet.getCoordinate();
		if(myCurrentCoordinate.getX() == enemy_bullet_coordinate.getX() && myCurrentCoordinate.getY() == enemy_bullet_coordinate.getY())
		{
			plane_was_hit = true;
		}
		return plane_was_hit;
	}
	
	/**
	 * shoot() loads one bullet.
	 */
	public void shoot()
	{
		Point my_location = new Point(myCurrentCoordinate.getX(), myCurrentCoordinate.getY());
		Bullet new_bullet = new Bullet(my_location, myFirePower, myDirection);
		myBullets.add(new_bullet);
	}
	
	
	/**
	 * updateBulletsCoordinate will move the bullet to the direction the plane is facing
	 */
	public void updateBulletsCoordinates()
	{
		for (int i = 0; i < myBullets.size(); i++)
		{
			Bullet this_bullet_location = myBullets.get(i);
			this_bullet_location.updateBullet(myScale);
		}
	}
	
	/**
	 * Removes the targeted bullet from the plane's bullet list.
	 * @param index
	 */
	public void removeBulletFromPath(final int index)
	{
		myBullets.remove(index);
	}
	
	/**
	 * 
	 * @return the location of all the bullets the plane shot
	 */
	public List<Point> getBulletsCoordinates()
	{
		List<Point> bullets_coordinates = new ArrayList<Point>();
		for(int i = 0; i < myBullets.size(); i++)
		{
			bullets_coordinates.add(myBullets.get(i).getCoordinate());
		}
		return bullets_coordinates;
	}
	
	/**
	 * Returns a list of bullets from the plane.
	 * @return Bullet myBullets
	 */
	public List<Bullet> getBullets()
	{
		return myBullets;
	}
	
	/**
	 * Report back the plane's current coordinate.
	 * @return Point myCurrentCoordinate
	 */
	public Point getCurrentLocation()
	{
		return myCurrentCoordinate;
	}
	
	/**
	 * Returns the value that indicates whether the plane is alive.
	 * @return boolean isAlive
	 */
	public boolean isAlive()
	{
		return isAlive;
	}
	
	/**
	 * Returns a representation of AbstractGalactica in string format.
	 * @return String myRepresentation
	 */
	public String toString()
	{
		StringBuilder myRepresentation = new StringBuilder();
		myRepresentation.append("(");
		myRepresentation.append(myCurrentCoordinate.getX());
		myRepresentation.append(",");
		myRepresentation.append(myCurrentCoordinate.getY());
		myRepresentation.append(")");
		return myRepresentation.toString();
	}

}
