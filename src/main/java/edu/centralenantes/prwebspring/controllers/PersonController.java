package edu.centralenantes.prwebspring.controllers;

import edu.centralenantes.prwebspring.items.Person;
import edu.centralenantes.prwebspring.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Contrôleur pour la gestion des utilisateurs.
 * @author Mohamed
 */
@Controller
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    /**
     * Affiche la liste des utilisateurs triée.
     * @param request
     * @return ModelAndView users
     */
    @RequestMapping(value = "users.do", method = RequestMethod.POST)
    public ModelAndView handlePostUsers(HttpServletRequest request) {
        List<Person> myList = new ArrayList<>(personRepository.findAll());
        Collections.sort(myList, Person.getComparator());
        
        ModelAndView returned = new ModelAndView("users");
        returned.addObject("usersList", myList);
        return returned;
    }

    /**
     * Redirige vers le formulaire d'édition ou la liste selon l'ID.
     * @param request
     * @return ModelAndView user ou users
     */
    @RequestMapping(value = "edituser.do", method = RequestMethod.POST)
    public ModelAndView handleEditUserPost(HttpServletRequest request) {
        String idStr = request.getParameter("id");
        int id = getIntFromString(idStr);
        
        if (id > 0) {
            Person person = personRepository.getReferenceById(id);
            ModelAndView returned = new ModelAndView("user");
            returned.addObject("user", person);
            return returned;
        } else {
            ModelAndView returned = new ModelAndView("users");
            returned.addObject("usersList", personRepository.findAll());
            return returned;
        }
    }

    /**
     * Enregistre les modifications d'un utilisateur et retourne à la liste.
     * @param request
     * @return ModelAndView users
     */
    @RequestMapping(value = "saveuser.do", method = RequestMethod.POST)
    public ModelAndView handlePostSaveUser(HttpServletRequest request) {
        ModelAndView returned;

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(PersonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // CORRECTION: utiliser les noms corrects des paramètres (camelCase comme dans le JSP)
        String idStr = request.getParameter("id");
        String firstName = request.getParameter("firstName");    // ✅ Corrigé
        String lastName = request.getParameter("lastName");      // ✅ Corrigé
        String birthdateStr = request.getParameter("birthdate"); // ✅ Corrigé
        
        // Validation des paramètres
        if (firstName == null || lastName == null || birthdateStr == null) {
            Logger.getLogger(PersonController.class.getName()).log(Level.WARNING, 
                "Paramètres manquants - firstName: " + firstName + ", lastName: " + lastName + ", birthdate: " + birthdateStr);
            returned = new ModelAndView("users");
            returned.addObject("usersList", personRepository.findAll());
            returned.addObject("error", "Tous les champs sont obligatoires");
            return returned;
        }
        
        Date birthday = getDateFromString(birthdateStr, "yyyy-MM-dd");
        
        if (birthday == null) {
            Logger.getLogger(PersonController.class.getName()).log(Level.WARNING, 
                "Date de naissance invalide: " + birthdateStr);
            returned = new ModelAndView("users");
            returned.addObject("usersList", personRepository.findAll());
            returned.addObject("error", "Date de naissance invalide");
            return returned;
        }
        
        int id = getIntFromString(idStr);
        
        // CORRECTION: vérifier si id <= 0 pour création, sinon mise à jour
        if (id <= 0) {
            // Création d'un nouvel utilisateur
            Logger.getLogger(PersonController.class.getName()).log(Level.INFO, 
                "Création utilisateur: " + firstName + " " + lastName);
            personRepository.create(firstName, lastName, birthday);
        } else {
            // Mise à jour d'un utilisateur existant
            Logger.getLogger(PersonController.class.getName()).log(Level.INFO, 
                "Mise à jour utilisateur ID " + id + ": " + firstName + " " + lastName);
            personRepository.update(id, firstName, lastName, birthday);
        }

        // Retour à la liste
        returned = new ModelAndView("users");
        Collection<Person> myList = personRepository.findAll();
        returned.addObject("usersList", myList);

        return returned;
    }
    
    /**
     * Supprime un utilisateur
     * @param request
     * @return ModelAndView users
     */
    @RequestMapping(value = "deleteuser.do", method = RequestMethod.POST)
    public ModelAndView handlePostDeleteUser(HttpServletRequest request){
        ModelAndView returned;

        String idStr = request.getParameter("id");
        int id = getIntFromString(idStr);
        personRepository.remove(id);

        returned = new ModelAndView("users");
        Collection<Person> myList = personRepository.findAll();
        returned.addObject("usersList", myList);

        return returned;
    }

    /**
     * Création d'un nouveau utilisateur et retourne à la liste.
     * @param request
     * @return ModelAndView users
     */
    @RequestMapping(value = "createuser.do", method = RequestMethod.POST)
    public ModelAndView handlePostCreateUser() {
        ModelAndView returned;

        Person newPerson = new Person();
        returned = new ModelAndView("user");
        returned.addObject("user", newPerson);

        return returned;
    }
    
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
            Logger.getLogger(PersonController.class.getName()).log(Level.WARNING, 
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
            Logger.getLogger(PersonController.class.getName()).log(Level.WARNING, 
                "Format entier invalide: " + value, ex);
        }
        return intValue;
    }
}