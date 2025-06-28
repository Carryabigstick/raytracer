package util;

import java.util.Random;

public class common
{

    private static final Random random = new Random();

    public static double degreesToRadians(double degrees)
    {
        return degrees * Math.PI / 180.0;
    }

    public static double infinity()
    {
        return Double.POSITIVE_INFINITY;
    }

    public static double PI()
    {
        return Math.PI;
    }

    public static double randomDouble()
    {
        // returns a random real number [0,1).
        return random.nextDouble();
    }

    public static double randomDouble(double min, double max)
    {
        // Returns a random real in [min,max);
        return min + (max-min) * randomDouble();
    }



}
