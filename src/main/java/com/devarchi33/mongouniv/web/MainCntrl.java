package com.devarchi33.mongouniv.web;

import com.devarchi33.mongouniv.domain.Person;
import com.devarchi33.mongouniv.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by donghoon on 2016. 6. 6..
 */
@RestController
@RequestMapping("/devarchi33")
public class MainCntrl {

    @Autowired
    private PersonService service;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        Person person = new Person("동훈", 31, "개발자");
        return service.save(person).toString();
    }
}
