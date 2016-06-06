package com.devarchi33.mongouniv;

import com.devarchi33.mongouniv.config.Properties;
import com.devarchi33.mongouniv.domain.Person;
import com.devarchi33.mongouniv.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;
import java.util.Map;

@SpringBootApplication
@EnableWebMvc
public class Application implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(Application.class);
    @Autowired
    private Properties properties;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private PersonService personService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        Map<String, Map<String, String>> servers = properties.getServers();
        Map<String, String> mongoInfo = servers.get("mongo");
        String host = mongoInfo.get("host");
        int port = Integer.parseInt(mongoInfo.get("port"));
        logger.info("Mongo host : {}, port: {}", host, port);

        mongoTemplate.dropCollection("persons");

        for (int i = 0; i < 10; i++) {
            Person person = new Person("동훈" + i, 31 + i, "개발자");
            personService.save(person);
        }

        List<Person> persons = personService.findAll();

        for (Person person : persons) {
            logger.info("Find Person name: {}, age: {}, profession: {}", person.getName(), person.getAge(), person.getProfession());
        }

        String collectionName = mongoTemplate.getCollectionName(Person.class);
        long count = personService.count();
        logger.info("{} count: {}", collectionName, count);

        Person nowAgePerson = personService.findByAge(31l);
        logger.info("Now age person name: {}, age: {}, profession: {}", nowAgePerson.getName(), nowAgePerson.getAge(), nowAgePerson.getProfession());

        List<Person> graterAgePersons = personService.findByGraterAge(35l);
        for (Person person : graterAgePersons) {
            logger.info("Grater Age Person name: {}, age: {}, profession: {}", person.getName(), person.getAge(), person.getProfession());
        }

        List<Person> betweenAgePersons = personService.findBetweenAge(33l, 39l);
        for (Person person : betweenAgePersons) {
            logger.info("Between Age Person name: {}, age: {}, profession: {}", person.getName(), person.getAge(), person.getProfession());
        }

        Sort ageSort = new Sort(Sort.Direction.DESC, "age");
        List<Person> sortedPersons = personService.findSorted(ageSort);
        for (Person person : sortedPersons) {
            logger.info("Sorted Age Person name: {}, age: {}, profession: {}", person.getName(), person.getAge(), person.getProfession());
        }
    }
}
