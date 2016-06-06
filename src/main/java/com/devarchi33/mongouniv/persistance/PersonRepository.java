package com.devarchi33.mongouniv.persistance;

import com.devarchi33.mongouniv.domain.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Created by donghoon on 2016. 6. 6..
 */
public interface PersonRepository extends MongoRepository<Person, String> {

    @Query(value = "{ 'age' : ?0 }")
    Person findByAge(long age);

    @Query(value = "{'age' : {$gt : ?0} }")
    List<Person> findByGraterAge(long age);

    @Query(value = "{'age' : {$gt : ?0, $lt : ?1} }")
    List<Person> findBetweenAge(long sAge, long eAge);

}
