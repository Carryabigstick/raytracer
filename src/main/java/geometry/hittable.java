package geometry;
import util.ray;
import util.Interval;


public interface hittable
{
    boolean hit(final ray r, Interval ray_t , HitRecord rec);
}
