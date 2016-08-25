package ru.dz.rmis.util;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.dz.rmis.filter.FilterProcessed;
import ru.dz.rmis.service.ImageService;
import ru.dz.rmis.model.ImageEntity;

import javax.imageio.ImageIO;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Nail on 21.08.2016.
 */
public class ImageConverterUtil {

    private ImageService imageService;

    public String getCoordByImage(Long id) throws IOException {

        ImageEntity imageEntity = imageService.getById(id);
        InputStream in = new ByteArrayInputStream(imageEntity.getImage());
        BufferedImage image = ImageIO.read(in);

        int start_x = getFirstPixelCoordinates(image)[0];
        int start_y = getFirstPixelCoordinates(image)[1];

        String coordinates = getAllCoord(image, start_x, start_y);

        return coordinates;
    }

    public byte[] getByteByImage(Image image) throws IOException {

        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();

        return imageInByte;
    }

    public Image getImageByCoord(String coordinates) throws IOException {

        JSONObject jObject = new JSONObject(coordinates); // json
        JSONObject data = jObject.getJSONObject("resolution"); // get data object

        JSONArray jArr = jObject.getJSONArray("coordinates");

        BufferedImage image = new BufferedImage(data.getInt("width"), data.getInt("height"),
                BufferedImage.TYPE_INT_ARGB);

        for (int i = 0; i < jArr.length(); i++) {
            JSONObject coordinate = jArr.getJSONObject(i);

            int x = coordinate.getInt("x");
            int y = coordinate.getInt("y");

            image.setRGB(x, y, Color.BLACK.getRGB());
        }

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
        //json
        coord.append("{resolution: {width: \"" + image.getWidth() + "\", height: \"" + image.getHeight() + "\"}, coordinates: [");

        while (coord_x < image.getWidth()) {

            int y_down = coord_y;
            int y_up = coord_y - 1;

            while (checkLuminanceDark(image, coord_x, y_down)) {
                coord.append("{x: \"" + coord_x + "\", y: \"" + y_down + "\"}, ");
                y_down++;
            }

            while (checkLuminanceDark(image, coord_x, y_up)) {
                coord.append("{x: \"" + coord_x + "\", y: \"" + y_up + "\"}, ");
                y_up--;
            }

            coord_y = (y_up + y_down) / 2;
            coord_x++;
        }

        coord.delete(coord.length() - 2, coord.length());
        coord.append("]}");

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

    public ImageEntity applyFilter(ImageEntity image) {
        List<ImageEntity> allImage = imageService.getAll();
        FilterProcessed filterProcessed = new FilterProcessed();
        try {
            Image imageAWT = getImageByCoord(image.getProcessedImage().getCoordinaatesJson());
            imageAWT = filterProcessed.apply(imageAWT);
            byte[] imageInByte = getByteByImage(imageAWT);
            image.setImage(imageInByte);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

}
