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

    public ray(ray other)
    {
        this.orig = other.orig;
        this.dir = other.dir;
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

    public void set(ray other) {
        this.orig = new vec3(other.orig);
        this.dir = new vec3(other.dir);
    }

    public void set(vec3 origin, vec3 direction)
    {
        this.orig = new vec3(origin);
        this.dir = new vec3(direction);
    }

}
