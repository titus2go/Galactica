package model;

public class Point {
	
	private final int myXCoord;
	private final int myYCoord;
	
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
