package material;

import util.*;
import geometry.HitRecord;

import static util.vec3.dot;
import static util.vec3.randomUnitVector;
import static util.vec3.reflect;
import static util.vec3.unit_vector;


public class Metal extends Material
{

    public color albedo;
    public double fuzz;


    public Metal(color albedo, double fuzz)
    {
        this.albedo = albedo;
        this.fuzz = fuzz;
    }

    @Override
    public boolean scatter(ray r_in, HitRecord rec, Wrapper<color> attenuation, Wrapper<ray> scattered)
    {
        vec3 reflected = reflect(r_in.direction(),rec.normal);

        // Some weird fuzz logic
        // Basically we jitter where the ray is supposed to go
        // and make sure its not going too far off track
        reflected = unit_vector(reflected) + (fuzz * randomUnitVector());


        scattered.value = new ray(rec.p,reflected);
        attenuation.value = albedo;

        return (dot(scattered.value.direction(), rec.normal) > 0);

    }
}
