package com.hibernate.hibernatedemo;

import com.hibernate.hibernatedemo.entity.Course;
import com.hibernate.hibernatedemo.repository.CourseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = HibernateDemoApplication.class)
public class JPQLTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    @Test
    public void jpql_basic(){
        List resultList = em.createQuery("Select c from Course c").getResultList();
        logger.info("Select c From Course c -> {}", resultList);

        /*
        Course[Microservices Tutorial],
        Course[JPA tutorial],
        Course[Spring tutorial],
        Course[Spring Boot tutorial],
        Course[Maxine tutorial]
         */
    }

    @Test
    public void jpql_typed() { //typed queries are always better
        TypedQuery<Course> query = em.createQuery("select c from Course c", Course.class); //expect results to match course.class
        List resultList = query.getResultList();
        logger.info("Select c From Course c -> {}", resultList);

    }

    @Test
    public void jpql_where() { //typed queries are always better
        TypedQuery<Course> query = em.createQuery("select c from Course c where name like '%Tutorial'", Course.class); //expect results to match course.class
        List resultList = query.getResultList();
        logger.info("Select c From Course c -> {}", resultList);

    }

    @Test
    public void jpql_coursesWithoutStudents() { //typed queries are always better
        TypedQuery<Course> query = em.createQuery("select c from Course c where c.students is empty", Course.class); //expect results to match course.class
        List resultList = query.getResultList();
        logger.info("Results -> {}", resultList);

    }


    @Test
    public void jpql_coursesWith2orMoreStudents() { //typed queries are always better
        TypedQuery<Course> query = em.createQuery("select c from Course c where size(c.students) >= 2", Course.class); //expect results to match course.class
        List resultList = query.getResultList();
        logger.info("Results -> {}", resultList);

    }

    @Test
    public void jpql_coursesOrderedByStudents() { //typed queries are always better
        TypedQuery<Course> query = em.createQuery("select c from Course c order by size(c.students)", Course.class); //expect results to match course.class
        List resultList = query.getResultList();
        logger.info("Results -> {}", resultList);

    }


    //JOIN => Select c, s from Course c JOIN c.students s
    //LEFT JOIN => Select c, s from Course c JOIN c.students s
        //left join would return courses with no students
    //cross join would take all students and all courses and join them even if they don't match
    @Test
    public void leftJoinTest(){
        Query query = em.createQuery("Select c, s from Course c JOIN c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results Size => {}", resultList.size());
        for (Object[] result : resultList){
            logger.info("Course{} Student{}", result[0],result[1]);
        }
    }


    @Test
    public void crossJoinTest(){
        Query query = em.createQuery("Select c, s from Course c, Student s");
        List<Object[]> resultList = query.getResultList();
        logger.info("Results Size => {}", resultList.size());
        for (Object[] result : resultList){
            logger.info("Course{} Student{}", result[0],result[1]);
        }
    }


}
