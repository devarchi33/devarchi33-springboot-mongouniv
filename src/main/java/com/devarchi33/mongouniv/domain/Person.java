package com.devarchi33.mongouniv.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by donghoon on 2016. 6. 6..
 */
@Document(collection = "persons")
@Data
public class Person {

    @Id
    private String id;
    private String name;
    private int age;
    private String profession;

    public Person(String name, int age, String profession) {
        this.name = name;
        this.age = age;
        this.profession = profession;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id:'" + id + '\'' +
                ", name:'" + name + '\'' +
                ", age:" + age +
                ", profession:'" + profession + '\'' +
                '}';
    }
}
