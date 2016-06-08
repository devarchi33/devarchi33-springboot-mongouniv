package com.devarchi33.mongouniv.service;

import com.devarchi33.mongouniv.domain.Student;

import java.util.List;

/**
 * Created by donghoon on 2016. 6. 8..
 */
public interface IStudentService {

    List<Student> findAll();
}
