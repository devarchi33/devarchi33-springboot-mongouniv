package com.devarchi33.mongouniv.service;

import com.devarchi33.mongouniv.domain.Person;
import com.mongodb.WriteResult;
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

    /**
     * ref: http://www.mkyong.com/mongodb/spring-data-mongodb-update-document/
     *
     * @param name
     * @param age
     * @return
     */
    WriteResult upsertAgeByName(String name, int age);

    WriteResult updateAllProfession(int age, String profession);

    /**
     * 첫번째 조건이 맞는 document 가 삭제된다.
     *
     * @param person
     */
    void deleteOne(Person person);

    void deleteAll();

    long count();

}
