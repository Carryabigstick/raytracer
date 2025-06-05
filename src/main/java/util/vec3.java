package util;

public class vec3 {

    public double[] e;

    public vec3()
    {
        e = new double[]{0.0,0.0,0.0};
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

    public vec3 negate()
    {
        this.e[0] *= -1;
        this.e[1] *= -1;
        this.e[2] *= -1;
        return this;
    }

    public vec3 unaryMinus()
    {
        return this.times(-1);
    }

    // I am not sure how to add the ability for vec3 to be indexed
    // aka vec3[2]


    public vec3 add(vec3 v)
    {
        this.e[0] += v.x();
        this.e[1] += v.y();
        this.e[2] += v.z();
        return this;
    }

    public vec3 plus(vec3 v) {
        return new vec3(x() + v.x(), y() + v.y(), z() + v.z());
    }

    public vec3 plus(int j)
    {
        return new vec3(x() + j, y() + j, z() + j);
    }

    public vec3 subtract(vec3 v)
    {
        this.e[0] -= v.x();
        this.e[1] -= v.y();
        this.e[2] -= v.z();
        return this;
    }

    public vec3 minus(vec3 v)
    {
        return new vec3(x() - v.x(), y() - v.y(), z() - v.z());
    }

    public vec3 multiply(double t)
    {
        this.e[0] *= t;
        this.e[1] *= t;
        this.e[2] *= t;
        return this;
    }

    public vec3 times(double t)
    {
        return new vec3(x() * t, y() * t, z() * t);
    }

    public vec3 divide(double t)
    {
        this.multiply(1/t);
        return this;
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

    // Vector utility functions

    public static vec3 negate(vec3 a)
    {
        return a.negate();
    }

    public static vec3 add(vec3 a, vec3 b)
    {
        return a.add(b);
    }

    public static vec3 subtract(vec3 a, vec3 b)
    {
        return a.subtract(b);
    }

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
        return v.divide(v.length());

    }

    @Override
    public String toString()
    {
        return this.x() + " " + this.y() + " " + this.z();
    }


}
