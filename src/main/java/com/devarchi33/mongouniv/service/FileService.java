package com.devarchi33.mongouniv.service;

import com.devarchi33.mongouniv.util.ResourceReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by donghoon on 2016. 6. 9..
 */
@Service
public class FileService implements IFileService {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private ResourceReader reader;

    @Override
    public void insertJsonFile(String location, String collection) throws IOException {
        Resource resource = reader.getResource(location);

        InputStream is = resource.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line;
        while ((line = br.readLine()) != null) {
            mongoTemplate.insert(line, collection);
        }
        br.close();
    }
}
