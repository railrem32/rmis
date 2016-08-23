package ru.dz.rmis.controller;

import java.util.Locale;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author vassaeve
 */
@Controller
@RequestMapping("/")
public class PageController {

    @RequestMapping(method = RequestMethod.GET)
    public String index(Locale locale, Model model) {
        return "images";
    }
}
