package main;
import display.DrawImage;
import geometry.HittableList;

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


        HittableList world = new HittableList();
        world.add(new geometry.Sphere(new util.vec3(0,0,-1),0.5) );
        world.add(new geometry.Sphere(new util.vec3(0,-100.5,-1),100) );
        RT.setWorld(world);

        Thread thread = new Thread(RT);
        thread.start();








    }
}
