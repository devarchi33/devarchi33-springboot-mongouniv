package com.devarchi33.mongouniv.persistance;

import com.devarchi33.mongouniv.domain.aggregation.AggStudent;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by donghoon on 2016. 6. 8..
 */
public interface AggStudentRepository extends MongoRepository<AggStudent, String> {
}
