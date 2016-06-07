package com.devarchi33.mongouniv.persistance;

import com.devarchi33.mongouniv.domain.Grade;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by donghoon on 2016. 6. 7..
 */
public interface GradeRepository extends MongoRepository<Grade, String> {
}
