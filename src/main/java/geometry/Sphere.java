package geometry;
import util.vec3;
import util.ray;

import static util.vec3.dot;

public class Sphere implements hittable
{
    // point that is the center of the sphere
    public vec3 center;

    // radius of sphere
    double radius;

    public Sphere(vec3 center, double radius)
    {
        this.center = center;
        this.radius = Math.max(0,radius);
    }

    public boolean hit(ray r, double ray_tmin, double ray_tmax, HitRecord rec)
    {
        // point - point3 = vec3
        // vec pointing from ray to center
        vec3 oc = center - r.origin();

        // Computes quadratic formula in a simplified way using h
        // which is derived from b. b = -2h
        var a = r.direction().length_squared();
        var h = dot(r.direction(), oc);
        var c = oc.length_squared() - radius * radius;

        var discriminant = h * h - a * c;

        if (discriminant < 0)
        {
            return false;
        }

        // rooted discriminant
        var sqrtd = Math.sqrt(discriminant);

        // find nearest square root
        var root =(h - sqrtd) / a;
        if(root <= ray_tmin || root >= ray_tmax)
        {
            root = ((h + sqrtd) / a);
            if(root <= ray_tmin || root >= ray_tmax)
            {
                return false;
            }
        }

        // if valid root, assign and calculate proper values to hit_record
        rec.t = root;
        rec.p = r.at(rec.t);
        rec.normal = (rec.p - center) / radius;

        return true;
    }
}
