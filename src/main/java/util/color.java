package util;

public class color extends vec3
{

    public int rbyte;
    public int gbyte;
    public int bbyte;

    public color(double e0, double e1, double e2)
    {
        super(e0,e1,e2);
    }

    public String write_color()
    {
        double r = this.x();
        double g = this.y();
        double b = this.z();

        // Translate the [0,1] component values to the byte range [0,255].
        // scales to integer values between 0-255 to work with PPM format
        rbyte = (int) (255.999 * r);
        gbyte = (int) (255.999 * g);
        bbyte = (int) (255.999 * b);
        return this.toString();
    }


    public int getRbyte() {
        return rbyte;
    }


    public void setRbyte(int rbyte) {
        this.rbyte = rbyte;
    }


    public int getGbyte() {
        return gbyte;
    }


    public void setGbyte(int gbyte) {
        this.gbyte = gbyte;
    }


    public int getBbyte() {
        return bbyte;
    }


    public void setBbyte(int bbyte) {
        this.bbyte = bbyte;
    }


    public String toString()
    {
        return (rbyte + " " + gbyte + " " + bbyte + "\n");
    }

    public color plus(color v) {
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

    public color div(double t)
    {
        return new color(x() / t, y() / t, z() / t);
    }

    public color unaryMinus()
    {
        return this.times(-1);
    }


}
