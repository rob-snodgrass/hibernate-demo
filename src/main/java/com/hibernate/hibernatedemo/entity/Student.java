package com.hibernate.hibernatedemo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false) //name cannot be null, prevents bad data from coming through
    private String name;

    public Student(String name) {
        this.name = name;
    }

    @OneToOne(fetch = FetchType.LAZY)   //will be a foreign key. Fetch type lazy will make it not search for this. ass soon as the em.find is executed it's done and won't seek more
    private Passport passport; //PASSPORT_ID added to the student table
                               //passport needs to be inserted before student table if referencing in H2


    protected Student() {} //JPA required a no argument constructor


    @ManyToMany(fetch = FetchType.EAGER) //by default many to many are lazy fetch, so when you fetch a student it wouldn't also fetch courses. Will do left outer join
    @JoinTable(name = "STUDENT_COURSE",   //re-labels the table to this name
    joinColumns = @JoinColumn(name = "STUDENT_ID"),
    inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))
    //joinColumn - STUDENT_ID
    //inverseJoinColumn - COURSE_ID
    //also creates foreign key constraints

    /*
    SELECT * FROM STUDENT_COURSE, student,course
    WHERE
    student_course.student_id = student.id AND
    student_course.course_ID=course.id
    */

    private List<Course> courses = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourses(Course course) {
        this.courses.add(course);
    }

    @Override
    public String toString() {
        return String.format("Student[%s]", name);
    }
}
