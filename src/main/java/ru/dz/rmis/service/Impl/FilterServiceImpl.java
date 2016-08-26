package ru.dz.rmis.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dz.rmis.model.Filter;
import ru.dz.rmis.repository.FilterRepository;
import ru.dz.rmis.service.FilterService;

@Service
public class FilterServiceImpl implements FilterService {

    @Autowired
    private FilterRepository filterRepository;

    @Override
    public void save(Filter filter) {
        filterRepository.save(filter);
    }

    @Override
    public void delete(Filter filter) {
        filterRepository.delete(filter);
    }

    @Override
    public List<Filter> findAll() {
        List<Filter> filters = filterRepository.findAll();
        return filters;
    }

    @Override
    public Filter findOne(Long id) {
        Filter filter = filterRepository.findOne(id);
        return filter;
    }

}
