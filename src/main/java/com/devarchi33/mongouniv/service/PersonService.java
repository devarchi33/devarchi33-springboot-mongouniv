package com.devarchi33.mongouniv.service;

import com.devarchi33.mongouniv.domain.Person;
import com.devarchi33.mongouniv.persistance.PersonRepository;
import com.mongodb.DBCollection;
import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by donghoon on 2016. 6. 6..
 */
@Service
public class PersonService implements IPersonService {

    @Autowired
    private PersonRepository repository;
    @Autowired
    private MongoTemplate template;

    @Override
    public Person save(Person person) {

        if (person == null)
            throw new NullPointerException("person object is null...");

        return repository.save(person);
    }

    @Override
    public List<Person> findAll() {
        return repository.findAll();
    }

    @Override
    public Person findByAge(long age) {
        return repository.findByAge(age);
    }

    @Override
    public List<Person> findByGraterAge(long age) {
        return repository.findByGraterAge(age);
    }

    @Override
    public List<Person> findBetweenAge(long sAge, long eAge) {
        return repository.findBetweenAge(sAge, eAge);
    }

    @Override
    public List<Person> findSorted(Sort sort) {
        Query query = new Query();
        query.with(sort);
        return template.find(query, Person.class);
    }

    @Override
    public WriteResult upsertAgeByName(String name, int age) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        Update update = new Update();
        update.set("age", age);
        return template.upsert(query, update, Person.class);
    }

    @Override
    public WriteResult updateAllProfession(int age, String profession) {
        Query query = new Query();
        query.addCriteria(Criteria.where("age").gte(age));
        Update update = new Update();
        update.set("profession", profession);
        return template.updateMulti(query, update, Person.class);
    }

    @Override
    public void deleteOne(Person person) {
        repository.delete(person);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public long count() {
        return repository.count();
    }

}
