package com.hibernate.hibernatedemo.entity;

import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class PartimeEmployee extends Employee{

    public PartimeEmployee(String name, BigDecimal hourlyWage) {
        super(name);
        this.hourlyWage = hourlyWage;
    }

    protected PartimeEmployee(){}

    private BigDecimal hourlyWage;
}
