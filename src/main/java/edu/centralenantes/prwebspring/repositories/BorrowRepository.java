
package edu.centralenantes.prwebspring.repositories;

import edu.centralenantes.prwebspring.items.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kwyhr
 */
@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Integer>, BorrowRepositoryCustom {
    
}
