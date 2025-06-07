package main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class DrawImage {

    public static void main(String[] args)
    {
        DrawImage DI = new DrawImage();
    }



    public DrawImage()
    {
        BufferedImage image = null;

        try
        {
            File imageFile =
                new File("testimage.jpg"); // Replace with your image path
            image = ImageIO.read(imageFile);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        int scaledWidth = 500;
        int scaledHeight = 500;

        assert image != null;
        Image myImage = image.getScaledInstance(scaledWidth, scaledHeight,
                java.awt.Image.SCALE_SMOOTH);

        JFrame frame = new JFrame("Image Display");


        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(myImage, 10, 10, null);
            }
        };


        // create Icon

//        JLabel label = new JLabel(icon);

        // create Jframe

        frame.add(panel, BorderLayout.CENTER);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(scaledWidth+20, scaledHeight+50);

        frame.setVisible(true);


    }


}
