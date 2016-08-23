package ru.dz.rmis.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dz.rmis.model.Image;
import ru.dz.rmis.repository.ImageRepository;
import ru.dz.rmis.service.ImageService;

import java.util.List;

/**
 * Created by Alex on 23.08.16.
 */
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Long save(Image image) {
        return imageRepository.save(image).getId();
    }

    @Override
    public void deleteById(Long id) {
        imageRepository.delete(id);
    }

    @Override
    public List<Image> getAll() {
        return imageRepository.findAll();
    }

    @Override
    public Image getById(Long id) {
        return imageRepository.findOne(id);
    }
}
