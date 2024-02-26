package com.hibernate.hibernatedemo.repository;

import com.hibernate.hibernatedemo.entity.Course;
import com.hibernate.hibernatedemo.entity.Passport;
import com.hibernate.hibernatedemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class StudentRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;

    public Student findById(Long id){
        return entityManager.find(Student.class, id);
    }

    public Student save(Student student){
        Long studentId = student.getId();
        if(studentId == null){
            //insert
            entityManager.persist(student);
        }
        else{
            //update
            entityManager.merge(student);
        }
        return student;
    }


    public void deleteById(Long id){
        Student student = findById(id);

        //making a transaction needs @Transactional so everything works or everything doesn't
        entityManager.remove(student);
//        return student;
    }


    public void saveStudentWithPassport(){

        Passport passport = new Passport("Z234567");
        entityManager.persist(passport);
        //You must commit this passport to the database first, otherwise it will result in an unsaved transient instance

        Student student1 = new Student("Max"); //Student is the owner, needs the passport ID before being created and added
        student1.setPassport(passport);
        entityManager.persist(student1);
        //results in Max (id 1) having passport (id 1)
         //SELECT * FROM STUDENT, PASSPORT where STUDENT.PASSPORT_ID=PASSPORT.ID
    }

    public void persistenceContextOperation() {
        //Retrieve Student
        Student student = entityManager.find(Student.class, 20001L);
        //persistence context (student)

        //Retrieve Passport
        Passport passport = student.getPassport();
        //persistence context (student, passport)

        //Update Passport
        passport.setNumber("E987654");
        //persistence context (student, passport++)

        //Update student
        student.setName("Ranga");
        //persistence context (student++, passport++)
    }

    public void insertStudentAndCourse(Student student, Course course){
        //Student student= new Student("Jack");
        //Course course = new Course("Microservices Tutorial");
        entityManager.persist(student);
        entityManager.persist(course);

        //establishing the relationship between student and course on both sides
        student.addCourses(course);
        course.addStudents(student);

        //then persist the owner side
        entityManager.persist(student);
    }

}


// SQL queries from tables
// JPQL queries from entities