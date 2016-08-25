package ru.dz.rmis.controller.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.MultipartConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.dz.rmis.model.ImageEntity;
import ru.dz.rmis.model.ImageTypeOf;
import ru.dz.rmis.model.dto.ImageDto;
import ru.dz.rmis.model.dto.ImagesDto;
import ru.dz.rmis.model.helpers.ImageEntityHelper;
import ru.dz.rmis.service.ImageService;
import ru.dz.rmis.util.ImageConverterUtil;

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

        ImageEntity entity = imageService.getById(id);
        if (entity != null) {
            return new ResponseEntity<>(imageEntityHelper.createDtoFromImageEntity(entity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") long id) {
        ImageEntity entity = imageService.getById(id);
        if (entity != null) {
            imageService.deleteById(id);
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list/{page}")
    public ResponseEntity<Object> list(
            @PathVariable int page,
            @RequestParam(value = "pageSize") int pageSize) {
        Page<ImageEntity> list = imageService.findAllByPage(page, pageSize);

        List< ImageDto> result = new ArrayList<>(list.getNumberOfElements());

        list.getContent().stream().forEach((img) -> {
            result.add(imageEntityHelper.createDtoFromImageEntity(img));
        });

        int size = (int) imageService.countAll();
        return new ResponseEntity<>(new ImagesDto(size, result), HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Object> create(
            @RequestParam(value = "imageId", required = false) String imageId,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "typeOf", required = false) String typeOf,
            @RequestParam(value = "file", required = false) MultipartFile file) {
        ImageEntity image = null;
        boolean save = false;
        if (!StringUtils.isEmpty(imageId) && !"undefined".equals(imageId)) {
            //Обновляем существующий скрипт
            try {
                image = imageService.getById(Long.parseLong(imageId));
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
                return new ResponseEntity<>(imageEntityHelper.createDtoFromImageEntity(image), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/apply/{id}", method = RequestMethod.POST)
    public String applyFilter(@PathVariable Long id){
        ImageConverterUtil imageConverterUtil = new ImageConverterUtil();
        List<ImageEntity> images = imageService.getAll();
        ImageEntity current_image = imageService.getById(id);
        for(ImageEntity img: images){
            if (img.getTypeOf().name().equals(current_image.getTypeOf().name())){
                img = imageConverterUtil.applyFilter(img);
                imageService.save(img);
            }
        }
        current_image = imageConverterUtil.applyFilter(current_image);
        imageService.save(current_image);
        return "redirect:/filters";
    }

}
