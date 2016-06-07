package com.devarchi33.mongouniv.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by donghoon on 2016. 6. 7..
 */
@Document(collection = "grades")
@Data
public class Grade {

    @Id
    private String id;
    private int student_id;
    private String type;
    private float score;
}