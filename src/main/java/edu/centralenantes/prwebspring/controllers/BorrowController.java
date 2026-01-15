package edu.centralenantes.prwebspring.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.http.HttpServletRequest;
import edu.centralenantes.prwebspring.items.Book;
import edu.centralenantes.prwebspring.items.Borrow;
import edu.centralenantes.prwebspring.items.Person;
import edu.centralenantes.prwebspring.repositories.BookRepository;
import edu.centralenantes.prwebspring.repositories.BorrowRepository;
import edu.centralenantes.prwebspring.repositories.PersonRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Contrôleur pour la gestion des emprunts.
 * @author ECN
 */
@Controller
public class BorrowController {
    
    @Autowired
    private BorrowRepository borrowRepository;
    
    @Autowired
    @Lazy
    private BookRepository bookRepository;
    
    @Autowired
    @Lazy
    private PersonRepository personRepository;
    
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
            Logger.getLogger(BorrowController.class.getName()).log(Level.WARNING, 
                "Format entier invalide: " + value, ex);
        }
        return intValue;
    }
    
    /**
     * Retour d'un livre emprunté.
     * Redirige vers la page user avec les emprunts mis à jour.
     * @param request
     * @return ModelAndView user
     */
    @RequestMapping(value = "returnBorrow.do", method = RequestMethod.POST)
    public ModelAndView handleReturn(HttpServletRequest request) {
        String borrowStr = request.getParameter("id");
        int borrowId = getIntFromString(borrowStr);
        
        String userStr = request.getParameter("userID");
        int userID = getIntFromString(userStr);
        
        // Effectuer le retour du livre
        Borrow borrow = borrowRepository.returnBook(borrowId);
        
        if (borrow != null) {
            Logger.getLogger(BorrowController.class.getName()).log(Level.INFO, 
                "Livre retourné - Emprunt ID: " + borrowId);
        } else {
            Logger.getLogger(BorrowController.class.getName()).log(Level.WARNING, 
                "Échec du retour - Emprunt ID: " + borrowId);
        }
        
        // Rafraîchir les données de l'utilisateur et retourner à la page user
        Person user = personRepository.getReferenceById(userID);
        
        ModelAndView returned = new ModelAndView("user");
        returned.addObject("user", user);
        returned.addObject("booksList", bookRepository.findAll());
        
        return returned;
    }
    
    /**
     * Ajout d'un nouvel emprunt.
     * Redirige vers la page user avec les emprunts mis à jour.
     * @param request
     * @return ModelAndView user
     */
    @RequestMapping(value = "addBorrow.do", method = RequestMethod.POST)
    public ModelAndView handleAddBorrow(HttpServletRequest request) {
        String userStr = request.getParameter("userID");
        int userID = getIntFromString(userStr);
        
        String bookStr = request.getParameter("bookID");
        int bookID = getIntFromString(bookStr);
        
        // Validation
        if (userID <= 0 || bookID <= 0) {
            Logger.getLogger(BorrowController.class.getName()).log(Level.WARNING, 
                "Paramètres invalides - userID: " + userID + ", bookID: " + bookID);
            
            Person user = personRepository.getReferenceById(userID > 0 ? userID : 1);
            ModelAndView returned = new ModelAndView("user");
            returned.addObject("user", user);
            returned.addObject("booksList", bookRepository.findAll());
            returned.addObject("error", "Veuillez sélectionner un livre");
            return returned;
        }
        
        Person user = personRepository.getReferenceById(userID);
        Book book = bookRepository.getReferenceById(bookID);
        
        // Créer l'emprunt
        borrowRepository.create(user, book);
        
        Logger.getLogger(BorrowController.class.getName()).log(Level.INFO, 
            "Nouvel emprunt créé - User: " + userID + ", Book: " + bookID);
        
        // Rafraîchir les données de l'utilisateur (borrowCollection mise à jour)
        user = personRepository.getReferenceById(userID);
        
        ModelAndView returned = new ModelAndView("user");
        returned.addObject("user", user);
        returned.addObject("booksList", bookRepository.findAll());
        
        return returned;
    }
}