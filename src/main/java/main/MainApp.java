package main;
import display.DrawImage;
import geometry.HittableList;
import material.*;
import util.color;

public class MainApp
{

    public static void main(String[] args)
    {
        double aspect_ratio = 16.0 / 9.0;
        int image_width = 1200;
        // calculate height based on width and the given aspect_ratio
        int image_height = (int)(image_width / aspect_ratio);

        DrawImage UI = new DrawImage(image_width,image_height);
        Raytracer RT = new Raytracer(image_width,image_height,aspect_ratio,UI);
        System.out.printf("New Raytracer Thread with following info: \n Width: %d \n " +
            "Height: %d\n",RT.image_width,RT.image_height);

        // Materials
        var metal1 = new Metal(new color(0.8, 0.8, 0.8),0.4);
        var metal2 = new Metal(new color(0.8, 0.8, 0.8),0.4);
        var blueDiffuse = new Lambertian(new color(0.1, 0.2, 0.5));
        var yellowDiffuse = new Lambertian(new color(0.5, 0.5, 0.5));




        HittableList world = new HittableList();
        // center sphere
        world.add(new geometry.Sphere(new util.vec3(0,0,-2),0.5,blueDiffuse));

        // right sphere
        world.add(new geometry.Sphere(new util.vec3(3,0.2,-3),0.7,metal1));

        // back sphere
        world.add(new geometry.Sphere(new util.vec3(-1,-0.25,-3),0.2,metal2));


        // ground
        world.add(new geometry.Sphere(new util.vec3(0,-100.5,-1),100,yellowDiffuse) );
        RT.setWorld(world);

        MemoryMonitor monitor = new MemoryMonitor();
        monitor.start();

        Thread thread = new Thread(RT);
        thread.start();






    }
}
