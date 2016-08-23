package ru.dz.rmis.helpers;

import ru.dz.rmis.dto.ImageDto;
import ru.dz.rmis.model.Image;

import java.io.IOException;

/**
 * Created by Alex on 23.08.16.
 */
public class ImageHelper {

    public static Image createImageFromDto(ImageDto imageDto) throws IOException {
        Image image = new Image();
        updateImageFromDto(image, imageDto);
        return image;
    }

    public static Image updateImageFromDto(Image image, ImageDto imageDto) throws IOException {
        image.setName(imageDto.getName());
        image.setStudyId(imageDto.getStudyId());
        image.setPatientId(imageDto.getPatientId());
        image.setDate(imageDto.getDate());
        image.setImage(imageDto.getMultipartFile().getBytes());
        return image;
    }

}
