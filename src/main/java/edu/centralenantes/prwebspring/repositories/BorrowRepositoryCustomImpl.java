/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.centralenantes.prwebspring.repositories;

import edu.centralenantes.prwebspring.items.Book;
import edu.centralenantes.prwebspring.items.Borrow;
import edu.centralenantes.prwebspring.items.Person;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kwyhr
 */
@Repository
public class BorrowRepositoryCustomImpl implements BorrowRepositoryCustom {

    @Autowired
    @Lazy
    private BorrowRepository borrowRepository;

    @Autowired
    @Lazy
    private BookRepository bookRepository;

    @Autowired
    @Lazy
    private PersonRepository personRepository;

    @Override
    public Borrow create(Person person, Book book, Date borrowDate) {
        // Ensure we have full data
        if (person != null) {
            person = personRepository.getReferenceById(person.getPersonId());
        }
        if (book != null) {
            book = bookRepository.getReferenceById(book.getBookId());
        }

        // Build new borrow
        if ((person != null) && (book != null) && (borrowDate != null)) {
            Borrow item = new Borrow();
            item.setBorrowDate(borrowDate);
            item.setPersonId(person);
            item.setBookId(book);
            item.setBorrowDate(borrowDate);
            borrowRepository.saveAndFlush(item);

            Optional<Borrow> result = borrowRepository.findById(item.getBorrowId());
            if (result.isPresent()) {
                item = result.get();

                // Set reverse fields
                person.getBorrowCollection().add(item);
                personRepository.saveAndFlush(person);
                book.getBorrowCollection().add(item);
                bookRepository.saveAndFlush(book);

                // return item
                return item;
            }
        }
        return null;
    }

    @Override
    public Borrow create(Person person, Book book) {
        Calendar aCalendar = Calendar.getInstance();
        Date borrowDate = aCalendar.getTime();
        return create(person, book, borrowDate);
    }

    @Override
    public void remove(Borrow item) {
        if (item != null) {
            item = borrowRepository.getReferenceById(item.getBorrowId());
        }
        if (item != null) {
            // Remove borrow from person and book
            Person person = item.getPersonId();
            person.getBorrowCollection().remove(item);

            Book book = item.getBookId();
            book.getBorrowCollection().remove(item);

            // delete borrow item
            personRepository.saveAndFlush(person);
            bookRepository.saveAndFlush(book);
            borrowRepository.delete(item);
        }
    }

    @Override
    public Borrow getByBorrowId(Integer borrowId) {
        Optional<Borrow> result = borrowRepository.findById(borrowId);
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }

    @Override
    public Borrow returnBook(Borrow item, Date date) {
        if (item != null) {
            item = borrowRepository.getReferenceById(item.getBorrowId());
        }
        if ((item != null) && (date != null)) {
            item.setBorrowReturn(date);
            borrowRepository.saveAndFlush(item);
            return item;
        }
        return null;
    }

    @Override
    public Borrow returnBook(Borrow item) {
        Calendar aCalendar = Calendar.getInstance();
        Date date = aCalendar.getTime();
        return returnBook(item, date);
    }

    @Override
    public Borrow returnBook(int borrowId) {
        if (borrowId > 0) {
            Borrow item = borrowRepository.getReferenceById(borrowId);
            return returnBook(item);
        }
        return null;
    }
}
