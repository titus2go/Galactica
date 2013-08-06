package Model;
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
	private BulletType bulletType;
	
	/**
	 * direction of the bullet
	 *
	 */
	private int my_direction;
	int my_x;
	int my_y;

	private int myDistanceTraveled = 0;

	public Bullet(Point coordinate, int damage, BulletType type)
	{
		myDamage = damage;
		myCoordinate = coordinate;
		my_x = coordinate.getX();
		my_y = coordinate.getY();
		if(type == BulletType.PLAYER_BULLET)
		{
			my_direction = -1;//up wards
		}
		else if (type == BulletType.ENEMY_BULLET)
		{
			my_direction = 1;//downwards
		}
	}
	/**
	 * Move the bullet
	 * @param scale Speed of the bullet
	 */
	public void shoot(final int speed)
	{
		my_y += my_direction * speed;
		myCoordinate = new Point(myCoordinate.getX() , my_y);
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