package util;
public class color extends vec3
{

    /**
     * x, y and z values exist from vec3 subclass that
     * correspond to r g and b values.
     */
    // translated bytes
    public int rbyte;
    public int gbyte;
    public int bbyte;

    public color(double e0, double e1, double e2)
    {
        super(e0,e1,e2);
    }

    public void calc_color()
    {
        double r = this.x();
        double g = this.y();
        double b = this.z();

        r = linearToGamma(r);
        g = linearToGamma(g);
        b = linearToGamma(b);

        // Translate the [0,1] component values to the byte range [0,255].
        // scales to integer values between 0-255 to work with PPM format

        // Creates an interval between 0-1 to clamp rgb values
        Interval intensity = new Interval(0.000,0.9999);
        rbyte = (int) (256 * intensity.clamp(r));
        gbyte = (int) (256 * intensity.clamp(g));
        bbyte = (int) (256 * intensity.clamp(b));

    }

    public String write_color()
    {
        this.calc_color();
        return this.toString();
    }


    public int getRbyte() {
        return rbyte;
    }


    public int getGbyte() {
        return gbyte;
    }


    public int getBbyte() {
        return bbyte;
    }


    public String toString()
    {
        return (rbyte + " " + gbyte + " " + bbyte + "\n");
    }

    public color plus(color v) {
        return new color(x() + v.x(), y() + v.y(), z() + v.z());
    }

    public color plus(vec3 v) {
        return new color(x() + v.x(), y() + v.y(), z() + v.z());
    }

    public color plus(int j)
    {
        return new color(x() + j, y() + j, z() + j);
    }

    public color minus(vec3 v)
    {
        return new color(x() - v.x(), y() - v.y(), z() - v.z());
    }

    public color times(double t)
    {
        return new color(x() * t, y() * t, z() * t);
    }

    public color multiply(color c)
    {
        return new color(e[0] * c.e[0], e[1] * c.e[1], e[2] * c.e[2]);
    }

    public color div(double t)
    {
        return new color(x() / t, y() / t, z() / t);
    }

    public color unaryMinus()
    {
        return this.times(-1);
    }

    double linearToGamma(double linear_component)
    {
        if(linear_component > 0)
        {
            return Math.sqrt(linear_component);
        }

        return 0.0;
    }


}
