package com.devarchi33.mongouniv.service;

import com.devarchi33.mongouniv.domain.Student;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by donghoon on 2016. 6. 8..
 */
public interface IStudentService {

    List<Student> findAll(Sort sort);
}
