package com.hibernate.hibernatedemo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity  //entity bean maps to rows in database
@Table(name = "Course") //defines the name of the table if difference from code
//NamedQueries( value = {NamedQuery(name="query_get_all_courses", query="Select c from course c"),
//              NamedQuery(name="query_get_like_courses", query="Select c from course c like '%TUTORIAL'")}   need to change below to em.createNamedQuery
//@NamedQuery(name="query_get_all_courses", query="Select c from course c")
public class Course {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name", nullable = false) //name cannot be null, prevents bad data from coming through
    private String name;

    //hibernate offering for things like last change and when it was added
    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;
    @CreatedDate
    private LocalDateTime createdDate;

    public Course(String name) {
        this.name = name;
    }

    protected Course() {} //JPA required a no argument constructor

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("Course[%s]", name);
    }
}
