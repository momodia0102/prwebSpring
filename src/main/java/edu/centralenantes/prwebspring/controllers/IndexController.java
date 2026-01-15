package edu.centralenantes.prwebspring.controllers;
import edu.centralenantes.prwebspring.repositories.BookRepository;
import edu.centralenantes.prwebspring.repositories.BorrowRepository;
import edu.centralenantes.prwebspring.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.util.Collection;
import edu.centralenantes.prwebspring.items.Person;

@Controller
public class IndexController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private BorrowRepository borrowRepository;
    
    @RequestMapping(value="index.do", method=RequestMethod.GET)
    public ModelAndView handleGet() {
        return new ModelAndView("index");
    }
    
    @RequestMapping(value="login.do", method=RequestMethod.POST)
    public void handlePostIndex(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        
        if ((login != null) && (login.equals("admin"))
                && (password != null) && (password.equals("admin"))) {
            request.getRequestDispatcher("users.do").forward(request, response);
        } else {
            request.getRequestDispatcher("index.do").forward(request, response);
        }
    }
}