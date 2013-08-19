package bwg4.util;

public class Coords 
{
	/** get next x coord from speed and direction */
	public static double getNextX(double x, double d, double s)
	{
		return x + (s * Math.cos(d * Math.PI / 180.0));
	}

	/** get next y coord from speed and direction */
	public static double getNextY(double y, double d, double s)
	{
		return y + (s * Math.sin(d * Math.PI / 180.0));
	}

	/** get next xy coords from speed and direction */
	public static double[] getNextXY(double x, double y, double d, double s)
	{
		double[] newCoords = new double[2];

		newCoords[0] = x + (s * Math.cos(d * Math.PI / 180.0));
		newCoords[1] = y + (s * Math.sin(d * Math.PI / 180.0));

		return newCoords;
	}

	/** get distance between two coords */
	public static double getDistance(double x1, double y1, double x2, double y2)
	{
		return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
	}

	/** get degree from second point */
	public static double getDegreeFromPoint(double x1, double y1, double x2, double y2)
	{
		return Math.atan2((y2 - y1), (x2 - x1)) * 180 / Math.PI;
	}
}
