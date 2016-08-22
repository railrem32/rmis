package ru.dz.rmis.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.dz.rmis.model.Filter;
import ru.dz.rmis.repository.FilterRepository;
import ru.dz.rmis.service.FilterService;

import java.util.List;

public class FilterServiceImpl implements FilterService {

    @Autowired
    private FilterRepository filterRepository;

    public void save(Filter filter) {
        filterRepository.save(filter);
    }

    public void delete(Filter filter) {
        filterRepository.delete(filter);
    }

    public List<Filter> findAll() {
        List<Filter> filters = filterRepository.findAll();
        return filters;
    }

    public Filter findOne(Long id) {
        Filter filter = filterRepository.findOne(id);
        return filter;
    }


}
