package edu.centralenantes.prwebspring.repositories;

import edu.centralenantes.prwebspring.items.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository  extends JpaRepository<Person, Integer>, PersonRepositoryCustom {
    // Spring Data JPA génère automatiquement les méthodes CRUD
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
}