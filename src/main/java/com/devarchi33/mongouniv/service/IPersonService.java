package com.devarchi33.mongouniv.service;

import com.devarchi33.mongouniv.domain.Person;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by donghoon on 2016. 6. 6..
 */
public interface IPersonService {

    Person save(Person person);

    List<Person> findAll();

    Person findByAge(long age);

    List<Person> findByGraterAge(long age);

    List<Person> findBetweenAge(long sAge, long eAge);

    List<Person> findSorted(Sort sort);

    long count();

}
