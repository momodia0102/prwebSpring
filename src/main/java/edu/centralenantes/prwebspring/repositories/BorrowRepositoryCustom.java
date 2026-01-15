
package edu.centralenantes.prwebspring.repositories;

import edu.centralenantes.prwebspring.items.Book;
import edu.centralenantes.prwebspring.items.Borrow;
import edu.centralenantes.prwebspring.items.Person;
import java.util.Date;

/**
 *
 * @author kwyhr
 */
public interface BorrowRepositoryCustom {

    /**
     * Create new Borrow
     * @param person
     * @param book
     * @param borrowDate
     * @return Borrow
     */
    public Borrow create(Person person, Book book, Date borrowDate);

    /**
     * Create new Borrow
     * @param person
     * @param book
     * @return Borrow
     */
    public Borrow create(Person person, Book book);

    /**
     * Remove Borrow
     * @param item
     */
    public void remove(Borrow item);

    /**
     * Get a Borrow from its ID
     * @param borrowId
     * @return Borrow
     */
    public Borrow getByBorrowId(Integer borrowId);


    /**
     * Borrower returns book
     * @param borrow
     * @param date
     * @return  
     */
    public Borrow returnBook(Borrow borrow, Date date);

    /**
     * Borrower returns book
     * @param borrow
     * @return  
     */
    public Borrow returnBook(Borrow borrow);
     
    /**
     * Borrower returns book
     * @param borrowId
     * @return  
     */
    public Borrow returnBook(int borrowId);    
}
