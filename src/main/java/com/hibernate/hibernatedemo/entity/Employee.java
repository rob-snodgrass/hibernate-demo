package com.hibernate.hibernatedemo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@MappedSuperclass //cannot be an entity too, will map the subclasses. Cannot query the employee class
//@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //single table is the default strategy
        //InheritanceType.TAVKE_PER_CLASS will do exactly what it says, one table per class
        //InheritanceType.JOINED will have a third combined of just employee names (since that's all they share), no duplication of columns

    //MY PERSONAL PREFERENCE is joined and single, superclass will result in performance hit and single is too simple

@DiscriminatorColumn(name = "EmployeeType") //will rename the column in the database
public abstract class Employee {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    protected Employee() {} //JPA required a no argument constructor

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
        return String.format("Employee[%s]", name);
    }
}
