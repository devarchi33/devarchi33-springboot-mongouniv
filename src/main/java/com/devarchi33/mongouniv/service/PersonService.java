package com.devarchi33.mongouniv.service;

import com.devarchi33.mongouniv.domain.Person;
import com.devarchi33.mongouniv.persistance.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by donghoon on 2016. 6. 6..
 */
@Service
public class PersonService implements IPersonService {

    @Autowired
    private PersonRepository repository;

    @Override
    public Person save(Person person) {

        if (person == null)
            throw new NullPointerException("person object is null...");

        return repository.save(person);
    }
}
