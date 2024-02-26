package com.hibernate.hibernatedemo;

import com.hibernate.hibernatedemo.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = HibernateDemoApplication.class)
public class CriteriaQueryTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager em;

    @Test
    public void jpql_basic(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        Root<Course> courseRoot = cq.from(Course.class);

        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List resultList = query.getResultList();
        logger.info("Select c From Course c -> {}", resultList);

    }



}
