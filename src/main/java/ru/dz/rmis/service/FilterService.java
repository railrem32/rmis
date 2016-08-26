package ru.dz.rmis.service;

import java.util.List;
import ru.dz.rmis.model.Filter;

public interface FilterService {

    void save(Filter filter);

    void delete(Filter filter);

    List<Filter> findAll();

    Filter findOne(Long id);

}
