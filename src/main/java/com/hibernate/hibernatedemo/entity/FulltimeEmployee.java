package com.hibernate.hibernatedemo.entity;

import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class FulltimeEmployee extends Employee{

    public FulltimeEmployee(String name, BigDecimal salary) {
        super(name);
        this.salary = salary;
    }

    protected FulltimeEmployee(){}

    private BigDecimal salary;
}
