package ru.dz.rmis.Tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Roman on 22.08.2016.
 */
public class Tools {
    private static void addWatermark(BufferedImage image) {

        try {
            BufferedImage overlay = ImageIO.read(new File(".\\watermark.png"));

            int w = Math.max(image.getWidth(), overlay.getWidth());
            int h = Math.max(image.getHeight(), overlay.getHeight());
            BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

            Graphics g = combined.getGraphics();
            g.drawImage(image, 0, 0, null);
            g.drawImage(overlay, 10, 0, null);

            ImageIO.write(combined, "PNG", new File(".\\image_result.png"));
            System.out.println("Overlaying is done!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
