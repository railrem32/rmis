package ru.dz.rmis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dz.rmis.model.ProcessedImage;

/**
 * Created by Alex on 22.08.16.
 */

public interface ProcessedImageRepository extends JpaRepository<ProcessedImage, Long> {
}
