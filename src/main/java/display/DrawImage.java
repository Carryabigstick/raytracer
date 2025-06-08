package display;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

public class DrawImage {


    public int width;
    public int height;
    public BufferedImage image;
    private final JPanel imagePanel;


    public DrawImage(int width, int height)
    {

        // Initialize image
        this.width = width;
        this.height = height;
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Initialize frame
        JFrame frame = new JFrame("Raytracing Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(this.width, this.height));

        // Make live image panel
        imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);
            }
        };

        // Create window
        frame.add(imagePanel);
        frame.pack();
        frame.setVisible(true);

    }




    public void refresh()
    {
        SwingUtilities.invokeLater(imagePanel::repaint);
        try{Thread.sleep(1);} catch(InterruptedException ignored){}
    }

    public void setPixel(int x,int y,util.color color)
    {
        color.calc_color();

        int pixelColor = ( (color.getRbyte() << 16 ) | (color.getGbyte()<< 8) | color.getBbyte()  );
        synchronized (image)
        {
            image.setRGB(x, height - y - 1, pixelColor);
        }

    }





}
