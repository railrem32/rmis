package ru.dz.rmis.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dz.rmis.model.ImageEntity;
import ru.dz.rmis.repository.ImageDao;
import ru.dz.rmis.service.ImageService;

/**
 *
 * @author vassaeve
 */
@Service
@Transactional
public class ImageImpl implements ImageService {

    @Autowired
    ImageDao imagesDao;

    @Override
    public List<ImageEntity> findAll() {
        return imagesDao.findAll();
    }

    @Override
    public ImageEntity findByPK(long pk) {
        return imagesDao.findByPK(pk);
    }

    @Override
    public List<ImageEntity> findAllByPage(int page, int pageSize) {
        return imagesDao.findAllByPage(page, pageSize);
    }

    @Override
    public Long countAll() {
        return imagesDao.countAll();
    }

    @Override
    public void save(ImageEntity script) {
        imagesDao.createOrUpdate(script);
    }

    @Override
    public void delete(ImageEntity entity) {
        imagesDao.delete(entity);
    }

}
