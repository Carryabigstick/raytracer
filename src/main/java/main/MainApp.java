package main;
import display.DrawImage;
import main.Raytracer;

public class MainApp
{

    public static void main(String[] args)
    {
        Raytracer RT = new Raytracer(800);
        System.out.printf("New Raytracer Thread with following info: \n Width: %d \n " +
            "Height: %d\n",RT.image_width,RT.image_height);

        DrawImage UI = new DrawImage(RT.image_width,RT.image_height);
        RT.setOutput(UI);

        Thread thread = new Thread(RT);
        thread.start();



    }
}
