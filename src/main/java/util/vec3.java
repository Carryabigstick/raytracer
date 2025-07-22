package util;

import static util.common.randomDouble;

public class vec3 {

    public double[] e;

    public vec3()
    {
        e = new double[]{0.0,0.0,0.0};
    }

    public vec3(vec3 v) {
        this();
        this.e[0] = v.e[0];
        this.e[1] = v.e[1];
        this.e[2] = v.e[2];
    }

    public vec3(double e0, double e1, double e2)
    {
        e = new double[3];
        this.e[0] = e0;
        this.e[1] = e1;
        this.e[2] = e2;
    }

    public double x()
    {
        return this.e[0];
    }

    public double y()
    {
        return this.e[1];
    }

    public double z()
    {
        return this.e[2];
    }

    public vec3 unaryMinus()
    {
        return this.times(-1);
    }

    // I am not sure how to add the ability for vec3 to be indexed
    // aka vec3[2]

    public vec3 plus(vec3 v) {
        return new vec3(x() + v.x(), y() + v.y(), z() + v.z());
    }

    public vec3 plus(int j)
    {
        return new vec3(x() + j, y() + j, z() + j);
    }

    public vec3 minus(vec3 v)
    {
        return new vec3(x() - v.x(), y() - v.y(), z() - v.z());
    }


    public vec3 times(double t)
    {
        return new vec3(x() * t, y() * t, z() * t);
    }

    public vec3 div(double t)
    {
        return new vec3(x() / t, y() / t, z() / t);
    }

    public double length_squared()
    {
        return (x()*x() + y()*y() + z()*z());
    }

    public double length()
    {
        return Math.sqrt(length_squared());
    }

    public double dot(vec3 v)
    {
        return this.e[0] * v.e[0]
            + this.e[1] * v.e[1]
            + this.e[2] * v.e[2];
    }

    /**
     * Checks if vector is near zero and returns false if so.
     * Threshold is 1e-8
     * @return boolean
     */
    public boolean near_zero()
    {
        var s = 1e-8;
        return Math.abs(x()) < s && Math.abs(y()) < s && Math.abs(z()) < s;
    }

    // Vector utility functions

    public static double dot(vec3 a, vec3 b)
    {
        return a.x() * b.x()
            + a.y() * b.y()
            + a.z() * b.z();
    }

    public static vec3 cross(vec3 u, vec3 v)
    {

        return new vec3(u.e[1] * v.e[2] - u.e[2] * v.e[1],
            u.e[2] * v.e[0] - u.e[0] * v.e[2],
            u.e[0] * v.e[1] - u.e[1] * v.e[0]);
    }

    public static vec3 unit_vector(vec3 v)
    {
        return v / (v.length());

    }

    public static vec3 random()
    {
        return new vec3(randomDouble(),randomDouble(),randomDouble());
    }

    public static vec3 random(double min, double max)
    {
        return new vec3(randomDouble(min,max),randomDouble(min,max),randomDouble(min,max));
    }

    public static vec3 randomUnitVector()
    {
        while (true)
        {
            // random vec
            var p = vec3.random();
            // length sqr
            var lensq = p.length_squared();
            if(1e-160 < lensq && lensq <= 1)
            {
                // normalize random vec
                return p / Math.sqrt(lensq);
            }
        }
    }

    public static vec3 randomOnHemisphere(vec3 normal)
    {
        vec3 on_unit_sphere = randomUnitVector();
        if(dot(on_unit_sphere,normal) > 0.0)
        {
            return on_unit_sphere;
        }
        else
        {
            return -on_unit_sphere;
        }
    }

    /**
     * Reflects vector v over vector n
     * @param v vec
     * @param n vec
     * @return reflected vector
     */
    public static vec3 reflect(vec3 v, vec3 n)
    {
        return v - 2*dot(v,n)*n;
    }




    @Override
    public String toString()
    {
        return this.x() + " " + this.y() + " " + this.z();
    }


}
