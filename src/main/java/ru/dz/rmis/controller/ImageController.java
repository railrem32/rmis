package ru.dz.rmis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dz.rmis.dto.ImageDto;
import ru.dz.rmis.helpers.ImageHelper;
import ru.dz.rmis.model.ImageEntity;
import ru.dz.rmis.service.ImageService;

import javax.validation.Valid;
import java.io.IOException;

/**
 * Created by Alex on 23.08.16.
 */
@Controller
@RequestMapping("/image")
public class ImageController extends BaseController {

    @Autowired
    private ImageService imageService;

    @RequestMapping(method = RequestMethod.GET)
    public String viewList(Model model) {
        model.addAttribute("images", imageService.getAll());
        return "images";
    }

    @RequestMapping(name = "/{id}", method = RequestMethod.GET)
    public String viewImage(@PathVariable Long id, Model model) {
        model.addAttribute("image", imageService.getById(id));
        return "image";
    }

    @RequestMapping(method = RequestMethod.POST, consumes = { "multipart/form-data" })
    public String createNew(@ModelAttribute("imageForm") @Valid ImageDto imageDto) throws IOException {
        ImageEntity image = ImageHelper.createImageFromDto(imageDto);
        Long id = imageService.save(image);
        return redirectToView("/image/" + id);
    }

    @RequestMapping(name = "/{id}", method = RequestMethod.DELETE)
    public String deleteImage(@PathVariable Long id) {
        imageService.deleteById(id);
        return redirectToView("/image");
    }

    @RequestMapping(name = "/{id}", method = RequestMethod.PUT, consumes = { "multipart/form-data" })
    public String updateImage(@PathVariable Long id, @ModelAttribute("imageForm") @Valid ImageDto imageDto) throws IOException {
        ImageEntity image = ImageHelper.updateImageFromDto(imageService.getById(id), imageDto);
        imageService.save(image);
        return redirectToView("/image/" + id);
    }
}
