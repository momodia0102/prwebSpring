package edu.centralenantes.prwebspring.repositories;

import edu.centralenantes.prwebspring.items.Book;

/**
 *
 * @author kwyhr
 */
public interface BookRepositoryCustom {

    /**
     * Create book
     * @param title
     * @param authors
     * @return 
     */
    public Book create(String title, String authors);

    /**
     * Remove book
     * @param id 
     */
    public void remove(int id);

    /**
     * Update book
     * @param id
     * @param title
     * @param authors
     * @return 
     */
    public Book update(int id, String title, String authors);

}
