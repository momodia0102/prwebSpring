package edu.centralenantes.prwebspring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller simple pour tester le démarrage
 */
@Controller
public class IndexController {
    
    @RequestMapping(value = "/index.do", method = RequestMethod.GET)
    public String index() {
        return "index";  
    }
}