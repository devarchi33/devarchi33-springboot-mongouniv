package com.devarchi33.mongouniv.service;

import com.devarchi33.mongouniv.domain.aggregation.AggStudent;
import com.devarchi33.mongouniv.persistance.AggStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by donghoon on 2016. 6. 8..
 */
@Service
public class AggStudentService implements IAggStudentService {

    @Autowired
    private AggStudentRepository repository;

    @Override
    public AggStudent save(AggStudent student) {
        return repository.save(student);
    }

    @Override
    public List<AggStudent> findAll(Sort sort) {

        return repository.findAll(sort);
    }
}
