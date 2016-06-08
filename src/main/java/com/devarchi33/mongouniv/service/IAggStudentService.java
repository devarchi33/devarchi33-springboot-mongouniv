package com.devarchi33.mongouniv.service;

import com.devarchi33.mongouniv.domain.aggregation.AggStudent;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by donghoon on 2016. 6. 8..
 */
public interface IAggStudentService {

    AggStudent save(AggStudent student);

    List<AggStudent> findAll(Sort sort);
}
