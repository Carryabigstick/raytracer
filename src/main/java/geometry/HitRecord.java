package geometry;
import util.vec3;

public class HitRecord
{
    // point
    public vec3 p;

    // normal vector
    public vec3 normal;

    // length along vector in which we reach our point
    public double t;

    public HitRecord(){}

}
