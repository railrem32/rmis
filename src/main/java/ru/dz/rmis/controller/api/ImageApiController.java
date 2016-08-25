package ru.dz.rmis.controller.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.MultipartConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.dz.rmis.model.ImageTypeOf;
import ru.dz.rmis.model.ImageEntity;
import ru.dz.rmis.model.dto.ImageDto;
import ru.dz.rmis.model.dto.ImagesDto;
import ru.dz.rmis.model.helper.ImageEntityHelper;
import ru.dz.rmis.service.ImageService;

/**
 *
 * @author vassaeve
 */
@RestController
@RequestMapping("/api/image")
@MultipartConfig(fileSizeThreshold = 20971520)
public class ImageApiController {

    @Autowired
    ImageService imageService;

    @Autowired
    ImageEntityHelper imageEntityHelper;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Object> get(@PathVariable("id") long id) {

        ImageEntity entity = imageService.findByPK(id);
        if (entity != null) {
            return new ResponseEntity<>(imageEntityHelper.createDto(entity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") long id) {
        ImageEntity entity = imageService.findByPK(id);
        if (entity != null) {
            imageService.delete(entity);
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list/{page}")
    public ResponseEntity<Object> list(
            @PathVariable int page,
            @RequestParam(value = "pageSize") int pageSize) {
        List<ImageEntity> list = imageService.findAllByPage(page, pageSize);
        List<ImageDto> result = new ArrayList<>(list.size());
        list.stream().forEach((image) -> {
            result.add(imageEntityHelper.createDto(image));
        });

        int size = imageService.countAll().intValue();
        return new ResponseEntity<>(new ImagesDto(size, result), HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Object> create(
            @RequestParam(value = "imageId") String imageId,
            @RequestParam(value = "description", required = true) String description,
            @RequestParam(value = "typeOf") String typeOf,
            @RequestParam(value = "file", required = false) MultipartFile file) {
        ImageEntity image = null;
        boolean save = false;
        if (!StringUtils.isEmpty(imageId) && !"undefined".equals(imageId)) {
            //Обновляем существующий скрипт
            try {
                image = imageService.findByPK(Long.parseLong(imageId));
            } catch (NumberFormatException ex) {
            }
        } else {
            image = new ImageEntity();
        }
        if (image != null) {
            if (!StringUtils.isEmpty(description)) {
                image.setDescription(description);
                save = true;
            }

            image.setTypeOf(ImageTypeOf.valueOf1(typeOf));

            if (file != null && !file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    image.setImage(bytes);
                    save = true;
                } catch (IOException ex) {
                    save = false;
                }
            }

            if (save) {
                imageService.save(image);
                return new ResponseEntity<>(new ImageDto(image.getId(), image.getDescription()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
