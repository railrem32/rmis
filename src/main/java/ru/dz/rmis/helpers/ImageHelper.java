package ru.dz.rmis.helpers;

import ru.dz.rmis.dto.ImageDto;
import ru.dz.rmis.model.ImageEntity;

import java.io.IOException;

/**
 * Created by Alex on 23.08.16.
 */
public class ImageHelper {

    public static ImageEntity createImageFromDto(ImageDto imageDto) throws IOException {
        ImageEntity image = new ImageEntity();
        updateImageFromDto(image, imageDto);
        return image;
    }

    public static ImageEntity updateImageFromDto(ImageEntity image, ImageDto imageDto) throws IOException {
        image.setName(imageDto.getName());
        image.setStudyId(imageDto.getStudyId());
        image.setPatientId(imageDto.getPatientId());
        image.setDate(imageDto.getDate());
        image.setImage(imageDto.getMultipartFile().getBytes());
        return image;
    }

}
