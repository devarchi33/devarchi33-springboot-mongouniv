package com.devarchi33.mongouniv.service;

import java.io.IOException;

/**
 * Created by donghoon on 2016. 6. 9..
 */
public interface IFileService {

    void insertJsonFile(String location, String collection) throws IOException;
}
