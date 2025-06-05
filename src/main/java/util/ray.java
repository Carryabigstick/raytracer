package util;

public class ray {

    // point3
    // Q value
    public vec3 orig;

    // vec3
    // d value
    public vec3 dir;


    public ray(final vec3 origin, final vec3 direction)
    {
        this.orig = origin;
        this.dir = direction;
    }


    // returns the function P(t) = Q + td
    public vec3 at(double t)
    {
        return (orig + (dir * t));
    }

    public vec3 origin()
    {
        return this.orig;
    }

    public vec3 direction()
    {
        return this.dir;
    }

}
