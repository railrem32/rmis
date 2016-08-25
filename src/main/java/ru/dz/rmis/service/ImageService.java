package ru.dz.rmis.service;

import org.springframework.cglib.util.StringSwitcher;
import ru.dz.rmis.model.ImageEntity;

import java.util.List;

/**
 * Created by Alex on 23.08.16.
 */
public interface ImageService {

    Long save(ImageEntity image);

    void deleteById(Long id);

    List<ImageEntity> getAll();

    ImageEntity getById(Long id);

    List<ImageEntity> findAllByPage(int page, int pageSize);

    long countAll();
}
