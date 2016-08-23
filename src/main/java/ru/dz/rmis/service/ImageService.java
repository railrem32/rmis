package ru.dz.rmis.service;

import ru.dz.rmis.model.Image;

import java.util.List;

/**
 * Created by Alex on 23.08.16.
 */
public interface ImageService {

    Long save(Image image);

    void deleteById(Long id);

    List<Image> getAll();

    Image getById(Long id);
}
