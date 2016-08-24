package ru.dz.rmis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dz.rmis.model.ImageEntity;

/**
 * Created by Alex on 22.08.16.
 */
@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
}
