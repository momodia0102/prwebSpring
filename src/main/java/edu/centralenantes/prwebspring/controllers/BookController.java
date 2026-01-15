package edu.centralenantes.prwebspring.controllers;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.http.HttpServletRequest;
import edu.centralenantes.prwebspring.items.Book;
import edu.centralenantes.prwebspring.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Contrôleur pour la gestion des livres.
 * @author kwyhr
 */
@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    /**
     * Convertit un String en Date.
     * @param aDate
     * @param format
     * @return Date ou null
     */
    private Date getDateFromString(String aDate, String format) {
        Date returnedValue = null;
        try {
            if (aDate != null && !aDate.trim().isEmpty()) {
                SimpleDateFormat aFormater = new SimpleDateFormat(format);
                returnedValue = aFormater.parse(aDate);
            }
        } catch (ParseException ex) {
            Logger.getLogger(BookController.class.getName()).log(Level.WARNING, 
                "Format date invalide: " + aDate, ex);
        }
        return returnedValue;
    }

    /**
     * Convertit un String en int.
     * @param value
     * @return int ou -1
     */
    private int getIntFromString(String value) {
        int intValue = -1;
        try {
            if (value != null && !value.trim().isEmpty()) {
                intValue = Integer.parseInt(value);
            }
        } catch (NumberFormatException ex) {
            Logger.getLogger(BookController.class.getName()).log(Level.WARNING, 
                "Format entier invalide: " + value, ex);
        }
        return intValue;
    }

    /**
     * Affiche la liste des livres.
     * @param request
     * @return ModelAndView books
     */
    @RequestMapping(value = "books.do")
    public ModelAndView handleLBooksPost(HttpServletRequest request) {
        ModelAndView returned = new ModelAndView("books");
        Collection<Book> myList = bookRepository.findAll();
        returned.addObject("booksList", myList);
        return returned;
    }

    /**
     * Redirige vers le formulaire d'édition ou la liste selon l'ID.
     * @param request
     * @return ModelAndView book ou books
     */
    @RequestMapping(value = "editbook.do", method = RequestMethod.POST)
    public ModelAndView handleEditBookPost(HttpServletRequest request) {
        String idStr = request.getParameter("id");
        int id = getIntFromString(idStr);

        if (id > 0) {
            Book book = bookRepository.getReferenceById(id);
            ModelAndView returned = new ModelAndView("book");
            returned.addObject("book", book);
            return returned;
        } else {
            ModelAndView returned = new ModelAndView("books");
            Collection<Book> myList = bookRepository.findAll();
            returned.addObject("booksList", myList);
            return returned;
        }
    }
    
    /**
     * Enregistre les modifications d'un livre et retourne à la liste.
     * @param request
     * @return ModelAndView books
     */
    @RequestMapping(value = "savebook.do", method = RequestMethod.POST)
    public ModelAndView handlePostSaveBook(HttpServletRequest request) {
        ModelAndView returned;

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String idStr = request.getParameter("id");
        String title = request.getParameter("title");
        String authors = request.getParameter("authors");
        
        // Validation des paramètres
        if (title == null || authors == null) {
            Logger.getLogger(BookController.class.getName()).log(Level.WARNING, 
                "Paramètres manquants - title: " + title + ", authors: " + authors);
            returned = new ModelAndView("books");
            returned.addObject("booksList", bookRepository.findAll());
            returned.addObject("error", "Tous les champs sont obligatoires");
            return returned;
        }
        
        int id = getIntFromString(idStr);
        
        if (id <= 0) {
            // Création d'un nouveau livre
            Logger.getLogger(BookController.class.getName()).log(Level.INFO, 
                "Création livre: " + title + " par " + authors);
            bookRepository.create(title, authors);
        } else {
            // Mise à jour d'un livre existant
            Logger.getLogger(BookController.class.getName()).log(Level.INFO, 
                "Mise à jour livre ID " + id + ": " + title);
            bookRepository.update(id, title, authors);
        }

        // Retour à la liste
        returned = new ModelAndView("books");
        Collection<Book> myList = bookRepository.findAll();
        returned.addObject("booksList", myList);

        return returned;
    }

    /**
     * Supprime un livre
     * @param request
     * @return ModelAndView books
     */
    @RequestMapping(value = "deletebook.do", method = RequestMethod.POST)
    public ModelAndView handlePostDeletebook(HttpServletRequest request) {
        String idStr = request.getParameter("id");
        int id = getIntFromString(idStr);
        bookRepository.remove(id);

        ModelAndView returned = new ModelAndView("books");
        Collection<Book> myList = bookRepository.findAll();
        returned.addObject("booksList", myList);

        return returned;
    }
    
    /**
     * Création d'un nouveau livre et retourne au formulaire.
     * @return ModelAndView book
     */
    @RequestMapping(value = "createbook.do", method = RequestMethod.POST)
    public ModelAndView handlePostCreatebook() {
        Book newBook = new Book();
        ModelAndView returned = new ModelAndView("book");
        returned.addObject("book", newBook);
        return returned;
    }
}