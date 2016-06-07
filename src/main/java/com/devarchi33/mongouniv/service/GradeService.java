package com.devarchi33.mongouniv.service;

import com.devarchi33.mongouniv.domain.Grade;
import com.devarchi33.mongouniv.persistance.GradeRepository;
import com.devarchi33.mongouniv.util.ResourceReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by donghoon on 2016. 6. 7..
 */
@Service
public class GradeService implements IGradeService {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private GradeRepository repository;
    @Autowired
    private ResourceReader reader;


    @Override
    public void insertJsonFile(String location) throws IOException {
        Resource resource = reader.getResource(location);

        InputStream is = resource.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line;
        while ((line = br.readLine()) != null) {
            mongoTemplate.insert(line, "grades");
        }
        br.close();
    }

    @Override
    public List<Grade> findAll(Sort sort) {
        return repository.findAll(sort);
    }
}