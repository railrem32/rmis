package ru.dz.rmis.service;

import java.util.List;
import org.springframework.data.domain.Page;
import ru.dz.rmis.model.ImageEntity;

/**
 * Created by Alex on 23.08.16.
 */
public interface ImageService {

    Long save(ImageEntity image);

    void deleteById(Long id);

    List<ImageEntity> getAll();

    ImageEntity getById(Long id);

    Page<ImageEntity> findAllByPage(int page, int pageSize);

    long countAll();
}
