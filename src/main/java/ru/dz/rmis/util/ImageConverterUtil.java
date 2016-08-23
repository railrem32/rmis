package ru.dz.rmis.util;

import ru.dz.rmis.service.ImageDetailService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Nail on 21.08.2016.
 */
public class ImageConverterUtil {


    private ImageDetailService imageDetailService;


    public String getCoordByImage() throws IOException {

        File f = new File("./test.PNG");
        BufferedImage image = ImageIO.read(f);
        int start_x = getFirstPixelCoordinates(image)[0];
        int start_y = getFirstPixelCoordinates(image)[1];

        String coordinates = getAllCoord(image, start_x, start_y);

        imageDetailService.add(coordinates);


        return coordinates;
    }


    public Image getImageByCoord(String coordinates) throws IOException {

        String[] coordinate = coordinates.split(" ");


        BufferedImage image = new BufferedImage(Integer.valueOf(coordinate[0]), Integer.valueOf(coordinate[1]),
                BufferedImage.TYPE_INT_RGB);

        for (int i = 2; i < coordinate.length; i = i + 2) {

            int x = Integer.valueOf(coordinate[i]);
            int y = Integer.valueOf(coordinate[i + 1]);

            image.setRGB(x, y, Color.WHITE.getRGB());

        }

        ImageIO.write(image, "png", new File("test_out.png"));

        return image;
    }







    private int[] getFirstPixelCoordinates(BufferedImage image) {

        int[] coord = new int[2];

        int x = 0;
        int y = 0;
        while (!checkLuminanceDark(image, x, y)) {
            y++;
            if (y == image.getHeight()) {
                x++;
                y = 0;
            }
        }
        coord[0] = x;
        coord[1] = y;

        return coord;
    }


    private String getAllCoord(BufferedImage image, int coord_x, int coord_y) {

        StringBuffer coord = new StringBuffer();

        coord.append(image.getWidth() + " " + image.getHeight() + " ");

        while (coord_x < image.getWidth()) {

            int y_down = coord_y;
            int y_up = coord_y - 1;

            while (checkLuminanceDark(image, coord_x, y_down)) {
                coord.append(coord_x + " " + y_down + " ");
                y_down++;
            }

            while (checkLuminanceDark(image, coord_x, y_up)) {
                coord.append(coord_x + " " + y_up + " ");
                y_up--;
            }

            coord_y = (y_up + y_down) / 2;
            coord_x++;
        }

        return coord.toString();

    }

    private boolean checkLuminanceDark(BufferedImage image, int x, int y) {

        int color = image.getRGB(x, y);
        int red = (color >>> 16) & 0xFF;
        int green = (color >>> 8) & 0xFF;
        int blue = (color >>> 0) & 0xFF;
        float luminance = (red * 0.2126f + green * 0.7152f + blue * 0.0722f) / 255;

        if (luminance < 0.5f) {
            // dark color
            return true;
        } else {
            // bright color
            return false;
        }

    }


}


