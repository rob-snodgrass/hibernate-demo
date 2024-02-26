package com.hibernate.hibernatedemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "course") //one course has many reviews, using the name of the variable INSIDE the review
    //anytime it's *ToOne it's lazy fetching
    private List<Review> reviewList = new ArrayList<>();


    /*
    //hibernate offering for things like last change and when it was added
    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;
    @CreatedDate
    private LocalDateTime createdDate;

     */

    @ManyToMany(mappedBy = "courses") //in order to avoid multiple join tables, make one side the owner (doesn't matter who) this makes student the owning side
    @JsonIgnore
    private List<Student> students = new ArrayList<>();

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

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void addReview(Review review) {
        this.reviewList.add(review);
    }

    public void removeReview(Review review){
        this.reviewList.remove(review);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudents(Student student) {
        this.students.add(student);
    }

    @Override
    public String toString() {
        return String.format("Course[%s]", name);
    }
}
