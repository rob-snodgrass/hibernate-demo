package com.hibernate.hibernatedemo.repository;

import com.hibernate.hibernatedemo.entity.Course;
import com.hibernate.hibernatedemo.entity.Review;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class CourseRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;

    public Course findById(Long id){
        return entityManager.find(Course.class, id);
    }

    public Course save(Course course){
        Long courseId = course.getId();
        if(courseId == null){
            //insert
            entityManager.persist(course);
        }
        else{
            //update
            entityManager.merge(course);
        }
        return course;
    }


    public void deleteById(Long id){
        Course course = findById(id);

        //making a transaction needs @Transactional so everything works or everything doesn't
        entityManager.remove(course);
//        return course;
    }


    public void playWithEntityManager(){
        //entityManager.persist();  //persist creates a new thing (entity)

        Course course1 = new Course("Web Services Tutorial");
        entityManager.persist(course1); //saves to "persistence context"
        entityManager.flush(); //changes up to this point are sent out to the database

        course1.setName("Web Services Updated");
        entityManager.flush();

//        entityManager.clear(); // will clear everything being tracked by entity manager or saved to database

        Course course2 = new Course("VueJS Tutorial");
        entityManager.persist(course2);
        entityManager.flush();

//        entityManager.detach(course2); //course 2 is no longer tracked by entity manager doing this

        course2.setName("VueJS Updated"); //will not be tracked / updated
        entityManager.refresh(course1); //will re-pull from the database to keep it 'fresh'

        entityManager.flush();

    }

    public void addReviewToCourse() {
        //get the course 1003
        Course course = findById(10003L);
        logger.info(" course.getReviews - {}",course.getReviewList());

        //add 2 reviews
        Review review1 = new Review("Was a blast","5");
        Review review2 = new Review("Learned so much","4");

        //setting the relationship
        course.addReview(review1);
        review1.setCourse(course); //they are being saved to each other
        course.addReview(review2);
        review2.setCourse(course);

        //save to the database
        entityManager.persist(review1);
        entityManager.persist(review2);
    }

    public void addReviewToCourse(Long courseId, List<Review> reviews) {
        Course course = findById(courseId);

        for(Review review : reviews) {
            //setting the relationship
            course.addReview(review);
            review.setCourse(course);
            //save to the database
            entityManager.persist(review);
        }
    }
}


// SQL queries from tables
// JPQL queries from entities