package edu.centralenantes.prwebspring.repositories;

import edu.centralenantes.prwebspring.items.Person;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PersonRepositoryCustomImpl implements PersonRepositoryCustom {

    @Autowired
    @Lazy
    private PersonRepository personRepository;

    @Override
    public Person update(int id, String firstName, String lastName, Date birthday) {
        Person item = null;
        if (id > 0) {
            item = personRepository.getReferenceById(id);
        }
        if ((item != null) && (firstName != null) && (lastName != null) && (birthday != null)) {
            item.setPersonFirstname(firstName);
            item.setPersonLastname(lastName);
            item.setPersonBirthdate(birthday);
            personRepository.saveAndFlush(item);
        }
        return item;
    }

    @Override
    public Person create(String firstName, String lastName, Date birthday) {
        if ((firstName != null) && (lastName != null) && (birthday != null)) {
            Person item = new Person();
            item.setPersonFirstname(firstName);
            item.setPersonLastname(lastName);
            item.setPersonBirthdate(birthday);
            personRepository.saveAndFlush(item);

            Optional<Person> result = personRepository.findById(item.getPersonId());
            if (result.isPresent()) {
                return result.get();
            }
        }
        return null;
    }

    @Override
    public void remove(int id) {
        Person item = null;
        if (id > 0) {
            item = personRepository.getReferenceById(id);
        }
        if (item != null) {
            personRepository.delete(item);
        }
    }
}
