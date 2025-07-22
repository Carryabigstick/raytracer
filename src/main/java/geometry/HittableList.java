package geometry;

import java.util.ArrayList;
import util.Interval;

public class HittableList implements hittable
{

    public ArrayList<hittable> hittables;

    public HittableList()
    {
        this.hittables = new ArrayList<>();
    }

    public HittableList(hittable obj)
    {
        this();
        hittables.add(obj);
    }

    public void clear()
    {
        hittables.clear();
    }

    public void add(hittable obj)
    {
        hittables.add(obj);
    }


    // loop through all hittable objects and well, compute hitting them.
    // Keep track of closest hit because we want the closest object to be on top.
    @Override
    public boolean hit(final util.ray r, Interval ray_t , HitRecord rec)
    {
        HitRecord tempRec = new HitRecord();
        boolean hitAnything = false;
        double closestSoFar = ray_t.max;

        for(hittable object : hittables)
        {
            if(object.hit(r, new Interval(ray_t.min,closestSoFar),tempRec))
            {
                hitAnything = true;
                closestSoFar = tempRec.t;
                // If object is hit, replace the general hit record with the results from this hit
                // In java this is done through copying the variables over
                // instead of rec = tempRec - which assigns the pointer of tempRec to rec - NOT what we want.
                rec.p = tempRec.p;
                rec.normal = tempRec.normal;
                rec.t = tempRec.t;
                rec.material = tempRec.material;
                rec.front_face = tempRec.front_face;
            }

        }

        return hitAnything;
    }




}
