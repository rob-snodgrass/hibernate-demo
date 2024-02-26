package com.hibernate.hibernatedemo;
import com.hibernate.hibernatedemo.entity.Course;
import com.hibernate.hibernatedemo.entity.Review;
import com.hibernate.hibernatedemo.repository.CourseRepository;
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
public class CourseRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseRepository repository = new CourseRepository();

    @Autowired
    private EntityManager em;

    @Test
    public void findByIdTest(){
        Course course = repository.findById(10001L);
        assertEquals("JPA tutorial",course.getName()) ;

        logger.info("Test is running");
    }

    @Test
    @Transactional
    public void findById_firstLevelCache(){
        Course course = repository.findById(10001L);
        logger.info("Test is running => {}",course);
        //no second query will be fired for the second one because it's within persistence context from @Transactional, and it's cached
        Course course1 = repository.findById(10001L);
        assertEquals("JPA tutorial",course.getName()) ;
        logger.info("Test is running => {}",course1);

        assertEquals("JPA tutorial",course.getName());
        assertEquals("JPA tutorial",course1.getName());
    }

    public void insertCourseTest(Course course) {

    }

    @Test
    @DirtiesContext //spring automatically resets the data after this test, so it's not changed at all for other tests
    public void deleteByIdTest(){
        repository.deleteById(10002L);
        assertNull(repository.findById(10002L));
    }

    @Test
    @DirtiesContext //spring automatically resets the data after this test, so it's not changed at all for other tests
    public void updateTest(){
        Course course1 = repository.findById(10001L);
        assertEquals("JPA tutorial", course1.getName());
        course1.setName("Updated Name");
        repository.save(course1);

        Course course2 = repository.findById(10001L);
        assertEquals("Updated Name", course2.getName());
    }


    @Test
    @DirtiesContext
    public void playWithEmTest(){
        repository.playWithEntityManager();
    }

    @Test
    @Transactional //need the persistence context to live longer than just pulling the course
    public void retrieveReviewsForCourse(){
        Course course = repository.findById(10001L);
        logger.info("course -> {}", course.getReviewList());
    }


    @Test
    @Transactional
    public void retrieveReviews(){
        Review review  = em.find(Review.class, 50001L);
        logger.info("course -> {}", review.getCourse());
    }


}
