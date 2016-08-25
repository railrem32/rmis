package ru.dz.rmis.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.dz.rmis.model.ImageEntity;
import ru.dz.rmis.repository.ImageRepository;
import ru.dz.rmis.service.ImageService;

/**
 * Created by Alex on 23.08.16.
 */
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Long save(ImageEntity image) {
        return imageRepository.save(image).getId();
    }

    @Override
    public void deleteById(Long id) {
        imageRepository.delete(id);
    }

    @Override
    public List<ImageEntity> getAll() {
        return imageRepository.findAll();
    }

    @Override
    public ImageEntity getById(Long id) {
        return imageRepository.findOne(id);
    }

    @Override
    public Page<ImageEntity> findAllByPage(int page, int pageSize) {
        PageRequest request = new PageRequest(page - 1, pageSize);
        Page<ImageEntity> result = imageRepository.findAll(request);

        return result;
    }

    @Override
    public long countAll() {
        return imageRepository.count();
    }
}
