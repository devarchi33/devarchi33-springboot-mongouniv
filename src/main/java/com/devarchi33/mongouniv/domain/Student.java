package com.devarchi33.mongouniv.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by donghoon on 2016. 6. 8..
 */
@Document(collection = "students")
@Data
public class Student {

    @Id
    private int id;
    private String name;
    private List<Score> scores;


    @Data
    public class Score {
        private String type;
        private float score;
    }
}
