package com.devarchi33.mongouniv.persistance;

import com.devarchi33.mongouniv.domain.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by donghoon on 2016. 6. 6..
 */
public interface PersonRepository extends MongoRepository<Person, String> {
}
