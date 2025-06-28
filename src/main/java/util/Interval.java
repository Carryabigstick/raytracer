package util;

import static util.common.infinity;


public class Interval
{

    public final double min;
    public final double max;

    public Interval()
    {
        min = Double.POSITIVE_INFINITY;
        max = Double.NEGATIVE_INFINITY;
    }

    public Interval(double min, double max)
    {
        this.min = min;
        this.max = max;
    }

    public double size()
    {
        return max - min;
    }

    public boolean contains(double x)
    {
        return min <= x && x <= max;
    }

    public boolean surrounds(double x)
    {
        return min < x && x < max;
    }





}
