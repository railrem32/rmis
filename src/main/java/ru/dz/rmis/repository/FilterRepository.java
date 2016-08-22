package ru.dz.rmis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dz.rmis.model.Filter;

import java.util.List;

@Repository
public interface FilterRepository extends JpaRepository<Filter, Long> {

    Filter save(Filter filter);
    void delete(Filter filter);
    List<Filter> findAll();
    Filter findOne(Long id);

}
