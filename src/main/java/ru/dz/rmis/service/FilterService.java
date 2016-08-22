package ru.dz.rmis.service;

import ru.dz.rmis.model.Filter;

import java.util.List;

public interface FilterService {

    void save(Filter filter);
    void delete(Filter filter);
    List<Filter> findAll();
    Filter findOne(Long id);

}
