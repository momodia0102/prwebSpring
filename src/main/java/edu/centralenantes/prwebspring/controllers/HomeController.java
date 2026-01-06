package edu.centralenantes.prwebspring.controllers;

import edu.centralenantes.prwebspring.repositories.BookRepository;
import edu.centralenantes.prwebspring.repositories.BorrowRepository;
import edu.centralenantes.prwebspring.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BorrowRepository borrowRepository;

    @RequestMapping(value = "index.do", method = RequestMethod.GET)
    public ModelAndView handleGet() {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("bookList", bookRepository.findAll());
        mv.addObject("personList", personRepository.findAll());
        mv.addObject("borrowList", borrowRepository.findAll());
        return mv;
    }
}
