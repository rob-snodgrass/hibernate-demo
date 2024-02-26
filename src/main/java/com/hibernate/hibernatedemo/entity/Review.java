package com.hibernate.hibernatedemo.entity;

import jakarta.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue
    private long id;

    private String rating;

    @Column(nullable = false)
    private String description;


    public Review(String name, String rating) {
        this.rating = rating;
        this.description = name;
    }

    protected Review() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String name) {
        this.description = name;
    }

    public long getId() {
        return id;
    }

    @ManyToOne //anytime it's *ToOne it's eager fetching
    private Course course;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return String.format("Review[%s]", description);
    }
}
