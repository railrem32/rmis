package ru.dz.rmis.model.helper;

import org.springframework.stereotype.Component;
import ru.dz.rmis.model.ImageEntity;
import ru.dz.rmis.model.dto.ImageDto;


/**
 *
 * @author vassaeve
 */
@Component
public class ImageEntityHelper {

    public ImageDto createDto(ImageEntity image) {
        ImageDto dto = new ImageDto(image.getId(), image.getDescription(), image.getTypeOf().name());
        return dto;
    }
}
