package util;

public class ray {

    // point3
    public vec3 orig;

    // vec3
    public vec3 dir;


    public ray(final vec3 origin, final vec3 direction)
    {
        this.orig = origin;
        this.dir = direction;
    }


    // returns the function P(t)
    public vec3 at(double t)
    {
        return (orig + (dir * t));
    }

}
