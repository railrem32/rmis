package ru.dz.rmis.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dz.rmis.model.Filter;
import ru.dz.rmis.service.FilterService;

import java.util.List;

@RestController
@RequestMapping("/api/filters")
public class FilterApiController {

    @Autowired
    private FilterService filterService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> filters () {
        List<Filter> filters = filterService.findAll();
        return new ResponseEntity<>(filters, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> add(@RequestParam String name, @RequestParam String className) {
        Filter filter = new Filter(name, className);
        filterService.save(filter);
        return new ResponseEntity<>(filter, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Filter filter = filterService.findOne(id);
        if (filter != null) {
            filterService.delete(filter);
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> edit(@PathVariable Long id) {
        Filter filter = filterService.findOne(id);
        if (filter != null) {
            return new ResponseEntity<>(filter, HttpStatus.OK);
        }
        return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> edit(@PathVariable Long id, @RequestParam String description, @RequestParam String className) {
        Filter filter = filterService.findOne(id);
        filter.setDescription(description);
        filter.setClassName(className);
        filterService.save(filter);
        return new ResponseEntity<>(filter, HttpStatus.OK);
    }

}
