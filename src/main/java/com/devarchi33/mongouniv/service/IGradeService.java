package com.devarchi33.mongouniv.service;

import com.devarchi33.mongouniv.domain.Grade;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.List;

/**
 * Created by donghoon on 2016. 6. 7..
 */
public interface IGradeService {

    List<Grade> findAll(Sort sort);

    void deleteOne(Grade grade);

    long count();

}
