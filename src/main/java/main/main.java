package main;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import util.color;
import util.ray;
import util.vec3;
import static util.vec3.dot;

public class main {

    public static void main(String[] args) {

        double aspect_ratio = 16.0 / 9.0;
        int image_width = 1920;

        // calculate height based on width and the given aspect_ratio
        int image_height = (int)(image_width / aspect_ratio);
        image_height = (image_height < 1) ? 1 : image_height;

        // Camera

        // default 1.0
        double focal_length = 5;

        // for now, 2.0 is arbitrary
        double viewport_height = 2.0;
        double viewport_width = viewport_height * (((double)image_width)/image_height);
        // uses imagew/imageh instead of aspect_ratio because that accounts for the actual
        // possible differences in the ratio because of double division.
        vec3 camera_center = new vec3(0,0,0);

        // x vector pointing horizontally along viewport
        vec3 viewport_u = new vec3(viewport_width,0,0);

        // y vector pointing vertically along viewport, negates because our coordinate system is backwards
        vec3 viewport_v = new vec3(0,-viewport_height,0);

        // calculate horizontal and vertical delta vectors from pixel to pixel
        vec3 pixel_delta_u = viewport_u / (double)image_width;
        vec3 pixel_delta_v = viewport_v / (double)image_height;

        // Calculate the location of the upper left pixel.
        // subtracts half of u and v because the vector camera center already starts halfway to each side
        vec3 viewport_upper_left = camera_center
            - new vec3(0, 0, focal_length) - viewport_u/2 - viewport_v/2;



        vec3 pixel00_loc = (viewport_upper_left + 0.5 * (pixel_delta_u + pixel_delta_v));




        // Render
        try
        {
            // creates buffered file writer, which is more
            // efficient for large amounts of text
            FileWriter writer = new FileWriter("frame.ppm");
            BufferedWriter bw = new BufferedWriter(writer);

            // header
            bw.write("P3\n" + image_width + " " + image_height + "\n255\n");

            for(int j = 0; j < image_height; j++)
            {
                System.out.printf("Scanlines remaining: %d\n",(image_height-j));

                for(int i = 0; i < image_width; i++)
                {
                    // point
                    vec3 pixel_center = (pixel00_loc + (i * pixel_delta_u) + (j * pixel_delta_v));
//                    System.out.println(pixel_center);


                    // calculates the direction
                    // Subtract two point3s → get a vec3 (the displacement between them)
                    vec3 ray_direction = pixel_center - camera_center;

                    // creates grid of vectors pointing to all pixels
                    ray r = new ray(camera_center , ray_direction);


                    // colors each pixel based on result of ray color
                    color pixel_color = ray_color(r);

//                    color pixel_color = new color(
//                        (double)i / (image_width-1),
//                        (double)j / (image_height-1),
//                        0.0
//                    );

                    bw.write(pixel_color.write_color());

                }
            }
            System.out.println("Done.");


            bw.close();


        } catch (IOException e)
        {
            System.out.println("An Error Occurred: " + e.getMessage());
        }


    }

    public static color ray_color(ray r)
    {
        color final_color;

        if(hit_sphere(new vec3(0.3,0,-3),0.1,r))
        {
            return new color(0.5137254902,0.2039215686,0.9215686275);
        }

        if(hit_sphere(new vec3(-0.3,0,-3),0.2,r))
        {
            return new color(1,0,0.1);
        }

        vec3 unit_direction = vec3.unit_vector(r.dir);

        // converts range of unit_direction from [-1,1] to [0,1]
        var a = 0.5 * (unit_direction.y() + 1.0);

        color startVal = new color(1,1,1);
        color endVal = new color(0.5,0.7,1.0);


        // smooth interpolation
        a = a * a * (3 - 2 * a);
        final_color = (1 - a) * startVal + a * endVal;

//        final_color = (1.0 - a) * startVal + a * endVal;

        return final_color;
    }

    public static Boolean hit_sphere(vec3 center, double radius, final ray r)
    {
        // point - point3 = vec3
        // vec pointing from ray to center
        vec3 oc = center - r.origin();

        var a = dot(r.direction(), r.direction());
        var b = -2.0 * dot(r.direction(), oc);
        var c = dot(oc, oc) - radius * radius;

        var discriminant = Math.sqrt(b * b - 4.0 * a * c);

        return (discriminant >= 0);

    }

}
