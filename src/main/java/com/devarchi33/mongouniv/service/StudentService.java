package com.devarchi33.mongouniv.service;

import com.devarchi33.mongouniv.domain.Student;
import com.devarchi33.mongouniv.persistance.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by donghoon on 2016. 6. 8..
 */
@Service
public class StudentService implements IStudentService {

    @Autowired
    private StudentRepository repository;

    @Override
    public List<Student> findAll(Sort sort) {

        return repository.findAll(sort);
    }
}
