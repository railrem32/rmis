package ru.dz.rmis.repository;

import java.util.List;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.dz.rmis.model.ImageEntity;

/**
 *
 * @author vassaeve
 */
@Repository
public class ImageDao extends AbstractDao<Long, ImageEntity> {

    public ImageDao() {
        super(ImageEntity.class);
    }

    public List<ImageEntity> findAllByPage(int page, int pageSize) {
        Query q = getSession().createQuery("from ImageEntity");
        q.setFirstResult((page - 1) * pageSize);
        q.setMaxResults(pageSize);
        return q.list();
    }
}
