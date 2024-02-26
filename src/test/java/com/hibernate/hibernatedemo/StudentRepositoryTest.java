package com.hibernate.hibernatedemo;
import com.hibernate.hibernatedemo.entity.Course;
import com.hibernate.hibernatedemo.entity.Passport;
import com.hibernate.hibernatedemo.entity.Student;
import com.hibernate.hibernatedemo.repository.CourseRepository;
import com.hibernate.hibernatedemo.repository.StudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest(classes = HibernateDemoApplication.class)
public class StudentRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StudentRepository repository = new StudentRepository();

    @Autowired
    EntityManager entityManager;

    @Test
    @Transactional
    public void retrieveStudentAndPassport(){
        Student student = entityManager.find(Student.class, 20001L);
        logger.info("Student -> {}",student); //will still do the left outer join to retrieve the student and passport details
        //logger.info("Passport -> {}",student.getPassport());
    }

    @Test
    //@Transactional //either everything happens   or nothing does
        //no longer needed because the studentrepository has a transactional around it
    //persistence context will track changes over time, *created at start of transaction and killed at end*
        //acts as the store for the entities being managed
    //session = persistence context in hibernate
    public void Test(){
        repository.persistenceContextOperation();
    }


    @Test
    @Transactional
    public void retrieveStudentAndCourse(){
        Student student = entityManager.find(Student.class, 20001L); //will not fetch course details, only student due to ManyToMany being a lazy fetch
        logger.info("Student -> {}",student);
        logger.info("Student courses -> {}",student.getCourses());


        //logger.info("Passport -> {}",student.getPassport());
    }



}
