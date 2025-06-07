package geometry;
import util.ray;


public interface hittable
{
    boolean hit(final ray r, double ray_tmin, double ray_tmax, HitRecord rec);
}
