import java.util.ArrayList;
import java.util.List;


public class AbstractGalacticaPlane {
	/*
	 * myTotalHealth is the total health that the plane has.
	 */
	private int myTotalHealth;
	
	/*
	 * myTotalLives
	 */
	private int myTotalLives;
	
	/*
	 * myHealthCount is the health that is left of the plane.
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
	 * myBulletsCoordinates provides the coordinates of the planes bullets.
	 */
	private List<Bullet> myBulletsCoordinates;
	
	/**
	 * myCurrentCoordinate provides the coordinate of the plane's current location.
	 */
	private Point myCurrentCoordinate;
	
	/*
	 * myDirection provides the direction in which the plane is facing and shooting
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
		myBulletsCoordinates = new ArrayList<Bullet>();
		myDirection = direction;
			
	}
	
	protected int getScale()
	{
		return myScale;
	}
	
	public void moveLeft()
	{
		myCurrentCoordinate = new Point(myCurrentCoordinate.getX() - myScale, myCurrentCoordinate.getY());
	}
	
	public void moveRight()
	{
		myCurrentCoordinate = new Point(myCurrentCoordinate.getX() + myScale, myCurrentCoordinate.getY());
	}
	
	public void moveUp()
	{
		myCurrentCoordinate = new Point(myCurrentCoordinate.getX(), myCurrentCoordinate.getY() - myScale);
	}
	
	public void moveDown()
	{
		myCurrentCoordinate = new Point(myCurrentCoordinate.getX(), myCurrentCoordinate.getY() + myScale);
		
	}
	
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
	 * Check whether the plane was hit using coordinates
	 * @param other_coordinate
	 * @return boolean value
	 */
	public boolean wasHit(Point other_coordinate)
	{	
		boolean plane_was_hit = false;
		if(myCurrentCoordinate.getX() == other_coordinate.getX() 
				&& myCurrentCoordinate.getY() == other_coordinate.getY())
		{
			plane_was_hit = true;
		}
		return plane_was_hit;
	}
	
	/**
	 * Check whether the plane was hit using Bullet class
	 * @param enemy_bullet
	 * @return boolean value
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
	 * shoot() loads one bullet
	 */
	public void shoot()
	{
		Point my_location = new Point(myCurrentCoordinate.getX(), myCurrentCoordinate.getY());
		Bullet new_bullet = new Bullet(my_location, myFirePower, myDirection);
		myBulletsCoordinates.add(new_bullet);
	}
	
	
	/**
	 * updateBulletsCoordinate will move the bullet to the direction the plane is facing
	 */
	public void updateBulletsCoordinates()
	{
		for (int i = 0; i < myBulletsCoordinates.size(); i++)
		{
			Bullet this_bullet_location = myBulletsCoordinates.get(i);
			this_bullet_location.updateBullet(myScale);
		}
	}
	
	public void removeBulletFromPath(final int index)
	{
		myBulletsCoordinates.remove(index);
	}
	
	/**
	 * 
	 * @return the location of all the bullets the plane shot
	 */
	public List<Point> getBulletsCoordinates()
	{
		List<Point> bullets_coordinates = new ArrayList<Point>();
		for(int i = 0; i < myBulletsCoordinates.size(); i++)
		{
			bullets_coordinates.add(myBulletsCoordinates.get(i).getCoordinate());
		}
		return bullets_coordinates;
	}
	
	/**
	 * 
	 * @return the plane's current location.
	 */
	public Point getCurrentLocation()
	{
		return myCurrentCoordinate;
	}
	
	/**
	 * @return boolean value indicating if the plane is alive or not.
	 */
	public boolean isAlive()
	{
		return isAlive;
	}
	
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
