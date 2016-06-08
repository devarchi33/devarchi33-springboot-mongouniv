package com.devarchi33.mongouniv.domain.aggregation;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by donghoon on 2016. 6. 8..
 */
@Document(collection = "agg_students")
@Data
public class AggStudent {

    @Id
    private int id;
    private String name;
    private Score scores;


    @Data
    public class Score {
        private String type;
        private float score;
    }
}
