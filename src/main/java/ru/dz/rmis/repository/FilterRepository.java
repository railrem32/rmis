package ru.dz.rmis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dz.rmis.model.Filter;

import java.util.List;

public interface FilterRepository extends JpaRepository<Filter, Long> {

    Filter save(Filter filter);
    void delete(Filter filter);
    List<Filter> findAll();
    Filter findOne(Long id);

}
