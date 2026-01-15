
package edu.centralenantes.prwebspring.repositories;

import edu.centralenantes.prwebspring.items.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kwyhr
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Integer>, BookRepositoryCustom {
    
}
