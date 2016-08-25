package ru.dz.rmis.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.dz.rmis.model.Filter;

public interface FilterRepository extends JpaRepository<Filter, Long> {

    Filter save(Filter filter);
    void delete(Filter filter);
    List<Filter> findAll();
    Filter findOne(Long id);

}
