package model;


public class Bullet {
	
	/**
	 * myDamage is the integer representing the damage count the bullet carries.
	 */
	private int myDamage;
	
	/**
	 * myCoordinate provides the location of the bullet in x and y coordinates.
	 */
	private Point myCoordinate;
	
	/**
	 * myDirection provides the direction of the bullet, 1 represent going upward, -1 represent going downwards
	 */
	private int myDirection;
	
	private int myDistanceTraveled = 0;
	
	public Bullet(Point coordinate, int damage, int direction)
	{
		myDamage = damage;
		myCoordinate = coordinate;
		myDirection = direction;
	}
	
	public void updateBullet(final int scale)
	{
		myCoordinate = new Point(myCoordinate.getX() + (myDirection * scale), myCoordinate.getY());
		myDistanceTraveled++;
	}
	
	public Point getCoordinate()
	{
		return myCoordinate;
	}
	
	public int getDamage()
	{
		return myDamage;
	}
	
	public int getDistanceTraveled()
	{
		return myDistanceTraveled;
	}
	
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("(");
		builder.append(myCoordinate.getX());
		builder.append(",");
		builder.append(myCoordinate.getY());
		builder.append(")");
		return builder.toString();
	}
	
	

}
