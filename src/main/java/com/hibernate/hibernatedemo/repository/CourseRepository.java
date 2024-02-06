package com.hibernate.hibernatedemo.repository;

import com.hibernate.hibernatedemo.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

}


// SQL queries from tables
// JPQL queries from entities