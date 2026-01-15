package edu.centralenantes.prwebspring.repositories;

import java.util.Date;
import edu.centralenantes.prwebspring.items.Person;

/**
 * Interface personnalisée pour les opérations sur Person.
 * @author Mohamed
 */
public interface PersonRepositoryCustom {
    
    /**
     * Crée une nouvelle personne.
     * @param firstName
     * @param lastName
     * @param birthday
     * @return Person
     */
    Person create(String firstName, String lastName, Date birthday);

    /**
     * Supprime une personne par son ID.
     * @param id 
     */
    void remove(int id);
    
    /**
     * Met à jour une personne existante.
     * @param id
     * @param firstName
     * @param lastName
     * @param birthday
     * @return Person
     */
    Person update( int id, String firstName, String lastName, Date birthday);
}