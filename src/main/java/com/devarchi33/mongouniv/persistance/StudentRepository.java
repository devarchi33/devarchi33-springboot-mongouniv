package com.devarchi33.mongouniv.persistance;

import com.devarchi33.mongouniv.domain.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by donghoon on 2016. 6. 8..
 */
public interface StudentRepository extends MongoRepository<Student, String> {
}
