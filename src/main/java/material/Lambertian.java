package material;

import util.*;
import geometry.HitRecord;
import static util.vec3.randomUnitVector;


public class Lambertian extends Material
{

    public color albedo;

    public Lambertian(color albedo)
    {
        this.albedo = albedo;
    }

    /**
     * There is apparently many different ways to approach
     * a Lambertian reflection
     *
     * @param r_in
     * @param rec
     * @param attenuation
     * @param scattered
     * @return
     */
    @Override
    public boolean scatter(ray r_in, HitRecord rec, Wrapper<color> attenuation,
                           Wrapper<ray> scattered)
    {
        vec3 scatter_direction = rec.normal + randomUnitVector();

        // catch bad scatter directions
        if(scatter_direction.near_zero())
        {
            scatter_direction = rec.normal;
        }

        // Initialize a ray from the normal adjusted
        // slightly with a random unit vector
        scattered.value = new ray(rec.p,scatter_direction);

        attenuation.value = albedo;

        return true;

    }

}
