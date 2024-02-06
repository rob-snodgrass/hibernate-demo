package com.hibernate.hibernatedemo;
import com.hibernate.hibernatedemo.entity.Course;
import com.hibernate.hibernatedemo.repository.CourseRepository;
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

    @Test
    public void findByIdTest(){
        Course course = repository.findById(10001L);
        assertEquals("JPA tutorial",course.getName()) ;

        logger.info("Test is running");
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


}
