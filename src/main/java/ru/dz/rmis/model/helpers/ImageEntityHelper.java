package ru.dz.rmis.model.helpers;

import org.springframework.stereotype.Component;
import ru.dz.rmis.model.ImageEntity;
import ru.dz.rmis.model.dto.ImageDto;

/**
 * Created by Alex on 23.08.16.
 */
@Component
public class ImageEntityHelper {

    public ImageEntity createImageFromDto(ImageDto imageDto) {
        ImageEntity image = new ImageEntity();
        updateImageFromDto(image, imageDto);
        return image;
    }

    public ImageEntity updateImageFromDto(ImageEntity image, ImageDto imageDto) {
        image.setDescription(imageDto.getDescription());
        image.setStudyId(imageDto.getStudyId());
        image.setPatientId(imageDto.getPatientId());
        image.setCreated(imageDto.getCreated());
        image.setImage(imageDto.getImageBytes());
        return image;
    }

    public ImageDto createDtoFromImageEntity(ImageEntity entity) {
        ImageDto dto = new ImageDto();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setPatientId(entity.getPatientId());
        dto.setStudyId(entity.getPatientId());
        dto.setImageBytes(entity.getImage());
        return dto;
    }

}
