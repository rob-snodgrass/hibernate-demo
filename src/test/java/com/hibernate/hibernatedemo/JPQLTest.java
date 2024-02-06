package com.hibernate.hibernatedemo;

import com.hibernate.hibernatedemo.entity.Course;
import com.hibernate.hibernatedemo.repository.CourseRepository;
import jakarta.persistence.EntityManager;
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
}
