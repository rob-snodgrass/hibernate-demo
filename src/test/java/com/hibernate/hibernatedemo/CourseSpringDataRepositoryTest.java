package com.hibernate.hibernatedemo;
import com.hibernate.hibernatedemo.entity.Course;
import com.hibernate.hibernatedemo.entity.Review;
import com.hibernate.hibernatedemo.repository.CourseRepository;
import com.hibernate.hibernatedemo.repository.CourseSpringDataRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = HibernateDemoApplication.class)
public class CourseSpringDataRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseSpringDataRepository repository;

    @Autowired
    private EntityManager em;

    @Test
    public void coursePresent(){
        Optional<Course> courseOptional = repository.findById(10001L); //latest change in JPA means it is optional to return to check if it exists or not
        assertTrue(courseOptional.isPresent());
        logger.info("{}", courseOptional.isPresent());
    }

    @Test
    public void courseNotPresent(){
        Optional<Course> courseOptional = repository.findById(80001L);
        assertFalse(courseOptional.isPresent());
        logger.info("{}", courseOptional.isPresent());
    }


    @Test
    public void mockTestSpringData(){
        Course course = new Course("Microservices again test");
        repository.save(course);
        //course is inserted in

        course.setName("Microservices updated name");
        repository.save(course);
        //course is updated

        logger.info("Courses => {}", repository.findAll()); //this jpa crud repository holds everything we need
        logger.info("Count => {}", repository.count());
    }

    @Test
    public void sortSpringData(){

        Sort sort = Sort.by(Sort.Direction.DESC, "name"); //new sort is deprecated , can add additional sort criteria using .and
        logger.info("Sorted Courses => {}", repository.findAll(sort));
    }

    @Test
    public void pagination() {
        //want to divide the data into pages
        PageRequest pageRequest = PageRequest.of(0,3); //page and size
        Page<Course> firstPage = repository.findAll(pageRequest);
        logger.info("First page => {}", firstPage.getContent());

        Pageable secondPageable = firstPage.nextPageable();
        Page<Course> secondPage = repository.findAll(secondPageable);
        logger.info("Second Page => {}",secondPage.getContent());
    }

    @Test
    public void findUsingName(){
        logger.info("FindByName=> {}", repository.findByName("JPA tutorial"));
       // logger.info("CountByName=> {}", repository.countByName("tutorial"));
    }

}
