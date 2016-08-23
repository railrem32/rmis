
package ru.dz.rmis.service;

import java.util.List;
import ru.dz.rmis.model.ImageEntity;

/**
 *
 * @author vassaeve
 */
public interface ImageService {
     public List<ImageEntity> findAll();

    public ImageEntity findByPK(long pk);

    public List<ImageEntity> findAllByPage(int page, int pageSize);

    public Long countAll();

    public void save(ImageEntity entity);

    public void delete(ImageEntity entity);
}
