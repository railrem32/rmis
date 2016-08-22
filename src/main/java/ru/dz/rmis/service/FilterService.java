package ru.dz.rmis.service;

import org.springframework.stereotype.Service;
import ru.dz.rmis.model.Filter;

import java.util.List;

@Service
public interface FilterService {

    void save(Filter filter);
    void delete(Filter filter);
    List<Filter> findAll();
    Filter findOne(Long id);

}
