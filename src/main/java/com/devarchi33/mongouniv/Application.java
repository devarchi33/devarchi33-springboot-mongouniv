package com.devarchi33.mongouniv;

import com.devarchi33.mongouniv.config.Properties;
import com.devarchi33.mongouniv.domain.aggregation.AggStudent;
import com.devarchi33.mongouniv.domain.Grade;
import com.devarchi33.mongouniv.domain.Person;
import com.devarchi33.mongouniv.domain.Student;
import com.devarchi33.mongouniv.service.*;
import com.mongodb.WriteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;
import java.util.*;

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
    @Autowired
    private GradeServiceI gradeService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private AggStudentService aggStudentService;
    @Autowired
    private FileService fileService;

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

//        gradeTest();
//        studentTest();
        studentTestHW_1();
    }

    private void studentTestHW_1() throws IOException {
        mongoTemplate.dropCollection("students");
        fileService.insertJsonFile("classpath:students.json", "students");

        Sort sort = new Sort(Sort.Direction.DESC, "_id");
        List<Student> students = studentService.findAll(sort);

        for (Student student : students) {
            List<Student.Score> scores = student.getScores();
            List<Student.Score> hw_scores = new ArrayList<>();
            List<Student.Score> new_scores = new ArrayList<>();
            for (Student.Score score : scores) {
                if (score.getType().equals("homework")) {
                    hw_scores.add(score);
                    hw_scores.sort((o1, o2) -> (int) (o2.getScore() - o1.getScore()));
                    if (hw_scores.size() == 2) {
                        hw_scores.remove(1);
                        new_scores.add(hw_scores.get(0));
                    }
                } else if (score.getType().equals("exam") || score.getType().equals("quiz")) {
                    new_scores.add(score);
                }
            }
            for (Student.Score score : new_scores) {
                logger.info("Student id: {}, name: {}, New Score type: {}, score: {}", student.getId(), student.getName(), score.getType(), score.getScore());
            }
            student.setScores(new_scores);


            /**
             *TODO: 다시 살펴보기 왜 _id 가 0 일 경우에만 duplicated id 가 되는지 모르겠음.
             * db.students.update({_id : 0}, {$pull : { "scores" :{score: 6.676176060654615 }} })
             * db.students.aggregate( [{ '$unwind': '$scores' },{'$group':{'_id': '$_id','average': { $avg: '$scores.score' }}},{ '$sort': { 'average' : -1 } }, { '$limit': 1 } ] )
             */
            if (student.getId() != 0)
                studentService.save(student);
        }
    }

    private void studentTest() {
        List<String> sortList = new ArrayList<>();
        sortList.add("_id");
        sortList.add("scores.score");
        Sort sort = new Sort(Sort.Direction.DESC, sortList);
        List<Student> students = studentService.findAll(sort);

        for (Student student : students) {
            List<Student.Score> scores = student.getScores();
            for (Student.Score score : scores) {
                logger.info("Student id: {}, name: {}, Score type: {}, score: {}", student.getId(), student.getName(), score.getType(), score.getScore());
            }
        }

        /**
         *TODO: hw3_1 의 집계 query 만들어 보기.
         */
        AggregationOperation unwind = Aggregation.unwind("scores");
        AggregationOperation aggSort = Aggregation.sort(Sort.Direction.ASC, "scores.score");
        Aggregation aggregation = Aggregation.newAggregation(unwind, aggSort);
        AggregationResults<AggStudent> aggStudents = mongoTemplate.aggregate(aggregation, mongoTemplate.getCollectionName(Student.class), AggStudent.class);

        for (AggStudent student : aggStudents) {
            /**
             * aggregation 이후 _id duplicate 문제.
             * TODO: group 이용하기.
             */
//            aggStudentService.save(student);
            logger.info("AggStudent name: {}, type: {}, score: {}", student.getName(), student.getScores().getType(), student.getScores().getScore());
        }
    }

    private void gradeTest() throws IOException {
        mongoTemplate.dropCollection("grades");
        fileService.insertJsonFile("classpath:grades.json", "grades");

        List<String> sortList = new ArrayList<>();
        sortList.add("student_id");
        sortList.add("score");
        Sort descScore = new Sort(Sort.Direction.DESC, sortList);
        List<Grade> grades = gradeService.findAll(descScore);
        int i = 1;
        for (Grade grade : grades) {

            /**
             * mongo univ hw2-3
             */
            if (i % 4 == 0) {
                gradeService.deleteOne(grade);
            }
            logger.info("Grade student_id: {}, type: {}, score: {}", grade.getStudent_id(), grade.getType(), grade.getScore());
            i++;
        }
        logger.info("Grade count: {}", gradeService.count());
    }

    private void personTest() {
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

        WriteResult updatedPerson = personService.upsertAgeByName("동훈2", 31);
        logger.info("Update Person: {}", updatedPerson.toString());

        WriteResult updatedPersons = personService.updateAllProfession(33, "개발자2");
        logger.info("Update Persons: {}", updatedPerson.toString());

        Person delPerson = personService.findByAge(31);
        personService.deleteOne(delPerson);
    }
}
