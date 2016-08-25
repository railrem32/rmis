package ru.dz.rmis.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dz.rmis.model.Filter;
import ru.dz.rmis.service.FilterService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/filters")
public class FilterController {//extends BaseController {

    @Autowired
    private FilterService filterService;

    @RequestMapping(method = RequestMethod.GET)
    public String filters () {
//        request.setAttribute("filters", filterService.findAll());
        return "filters";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String add(@RequestParam String name, @RequestParam String className) {
        Filter filter = new Filter(name, className);
        filterService.save(filter);
        return "redirect:/filters";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id) {
        Filter filter = filterService.findOne(id);
        if (filter != null) {
            filterService.delete(filter);
        }
        return "redirect:/filters";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable Long id) {
//        request.setAttribute("filter", filterService.findOne(id));
        return "edit_filter";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String edit(@PathVariable Long id, @RequestParam String name, @RequestParam String className) {
        Filter filter = filterService.findOne(id);
        filter.setName(name);
        filter.setClassName(className);
        filterService.save(filter);
        return "redirect:/filters";
    }

}
