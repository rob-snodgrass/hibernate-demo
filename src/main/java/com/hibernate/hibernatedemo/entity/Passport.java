package com.hibernate.hibernatedemo.entity;

import jakarta.persistence.*;

@Entity
public class Passport {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String number;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "passport") //this mappedBy will make the STUDENT the owner side of the relationship. added on the NON OWNING side of the relationship
    private Student student;

    public Passport(String name) {
        this.number = name;
    }

    protected Passport() {}

    public String getNumber() {
        return number;
    }

    public void setNumber(String name) {
        this.number = name;
    }

    public long getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return String.format("Passport[%s]", number);
    }
}
