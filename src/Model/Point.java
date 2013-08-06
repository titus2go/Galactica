package Model;

public class Point {

	private  int myXCoord;
	private  int myYCoord;

	public Point(final int x, final int y)
	{
		myXCoord = x;
		myYCoord = y;
	}

	public int getX()
	{
		return myXCoord;
	}

	public int getY()
	{
		return myYCoord;
	}
	/**
	 * set a new x-coordinate
	 * @param x The new x coordinate
	 */
	public void setX(int x)
	{
		myXCoord += x;
	}
	/**
	 * Set a new y-coordinate
	 * @param y The new y-coordinate
	 */
	public void setY(int y)
	{
		myYCoord += y;
	}
	public boolean isEqual(final Point the_other_point)
	{
		boolean isTheSame = false;
		if(myXCoord == the_other_point.getX() && myYCoord == the_other_point.getY())
		{
			isTheSame = true;
		}
		return isTheSame;
	}

}
