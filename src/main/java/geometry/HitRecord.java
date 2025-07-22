package geometry;
import util.vec3;
import util.ray;
import material.*;

import static util.vec3.dot;

public class HitRecord
{
    // point
    public vec3 p;

    // normal vector
    public vec3 normal;

    // Material
    public Material material;

    // length along vector in which we reach our point
    public double t;

    // true if the hit face is a front face.
    public boolean front_face;

    public HitRecord() {}


    public void set_face_normal(ray r, vec3 outward_normal)
    {
        front_face = dot(r.direction(),outward_normal) < 0;
        if(front_face)
        {
            normal = outward_normal;
        }
        else
        {
            normal = -outward_normal;
        }
    }

    public void set(HitRecord rec)
    {
        this.p = new vec3(rec.p);
        this.normal = new vec3(rec.normal);
        this.material = rec.material;
    }


}
