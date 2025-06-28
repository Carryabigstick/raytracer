package main;

import util.*;
import geometry.*;
import static util.common.infinity;

import display.DrawImage;

import static util.vec3.unit_vector;

public class Raytracer implements Runnable
{

    public double aspect_ratio;
    public final int image_width;
    public int image_height;
    public final double focal_length;

    public double viewport_height;
    public double viewport_width;

    public vec3 camera_center;
    public vec3 viewport_u;
    public vec3 viewport_v;

    public vec3 pixel_delta_u;
    public vec3 pixel_delta_v;


    public vec3 viewport_upper_left;
    public vec3 pixel00_loc;

    public HittableList world;

    DrawImage output;
    //
    public Raytracer()
    {
        this.image_width = 400;

        // default 1.0
        focal_length = 1.0;
        this.init();
    }

    public Raytracer(int image_width)
    {
        this.image_width = image_width;
        // default 1.0
        focal_length = 1.0;
        this.init();
    }

    public void setOutput(DrawImage DrawnImage)
    {
        this.output = DrawnImage;
    }

    public void init()
    {
        // Image
        aspect_ratio = 16.0 / 9.0;

        // calculate height based on width and the given aspect_ratio
        image_height = (int)(image_width / aspect_ratio);
        image_height = (image_height < 1) ? 1 : image_height;


        // World

        world = new HittableList();

        world.add(new Sphere(new vec3(0,0,-1),0.5) );
        world.add(new Sphere(new vec3(0,-100.5,-1),100) );


        // Camera

        // for now, 2.0 is arbitrary
        viewport_height = 2.0;
        viewport_width = viewport_height * (((double)image_width)/image_height);
        // uses imagew/imageh instead of aspect_ratio because that accounts for the actual
        // possible differences in the ratio because of double division.
        camera_center = new util.vec3(0,0,0);

        // x vector pointing horizontally along viewport
        viewport_u = new util.vec3(viewport_width,0,0);

        // y vector pointing vertically along viewport, negates because our coordinate system is backwards
        viewport_v = new util.vec3(0,-viewport_height,0);

        // calculate horizontal and vertical delta vectors from pixel to pixel
        pixel_delta_u = viewport_u / (double)image_width;
        pixel_delta_v = viewport_v / (double)image_height;

        // Calculate the location of the upper left pixel.
        // subtracts half of u and v because the vector camera center already starts halfway to each side
        viewport_upper_left = camera_center
            - new util.vec3(0, 0, focal_length) - viewport_u/2 - viewport_v/2;

        pixel00_loc = (viewport_upper_left + 0.5 * (pixel_delta_u + pixel_delta_v));

    }

    // Starts running component that generate scanlines
    public void run()
    {
        // j = vertical lines from top to bottom
        // i = horizontal lines from left to right
        for(int j = 0; j < image_height; j++)
        {
//            System.out.printf("Scanlines remaining: %d\n",(image_height-j));

            for(int i = 0; i < image_width; i++)
            {
                // point
                vec3 pixel_center = (pixel00_loc + (i * pixel_delta_u) + (j * pixel_delta_v));

                // calculates the direction
                // Subtract two point3s → get a vec3 (the displacement between them)
                vec3 ray_direction = pixel_center - camera_center;

                // creates ray for current pixel, pointing from camera to pixel
                ray r = new ray(camera_center , ray_direction);

                // colors each pixel based on result of ray color
                // passes in world so we can compare every ray
                // against what is in our world.
                color pixel_color = ray_color(r,world);

                output.setPixel(i,image_height-j-1, pixel_color);

            }
            output.refresh();
        }
        System.out.println("Rendering Complete.");
    }


    // Determines ray color taking in the current ray and world - the
    // list of hittable objects
    public static color ray_color(ray r, HittableList world)
    {
        HitRecord rec = new HitRecord();

        // Hit Object
        // if an object is hit, return the normal mapping as color data
        // which will result in it be mapped as UVs are by default
        if(world.hit(r,new Interval(0,infinity()), rec))
        {

            return 0.5 * (new color(1,1,1) + rec.normal);
        }

        // Not hit object

        vec3 unitDirection = unit_vector(r.direction());
        var a = 0.5*(unitDirection.y() + 1.0);

        // Creates color gradient for background
        color startVal = new color(1,1,1);
        color endVal = new color(0.5,0.7,1.0);

        return (1.0-a) * startVal + a * endVal;
    }




    // DEPRECIATED
//    public static color ray_color(ray r)
//    {
//        color final_color;
//
//        HitRecord rec = new HitRecord();
//
//        geometry.Sphere sphere01 = new Sphere(new vec3(0,0,-1),0.5);
//
//        sphere01.hit(r,0,1000,rec);
//
//        var t = rec.t;
//
//        if(t > 0.0)
//        {
//            // N is a vector because point - point = vector
//            vec3 N = unit_vector(r.at(t) - sphere01.center);
//
//            // remaps [-1,1] range of unit_vector N to rgb range of [0,1]
//            return (0.5 * new color(N.x()+1,N.y()+1,N.z()+1));
//        }
//
//        vec3 unit_direction = vec3.unit_vector(r.dir);
//
//        // converts range of unit_direction from [-1,1] to [0,1]
//        var a = 0.5 * (unit_direction.y() + 1.0);
//
//        color startVal = new color(1,1,1);
//        color endVal = new color(0.5,0.7,1.0);
//
//        // smooth interpolation
//        a = a * a * (3 - 2 * a);
//        final_color = (1 - a) * startVal + a * endVal;
//
//
//        return final_color;
//    }


}
