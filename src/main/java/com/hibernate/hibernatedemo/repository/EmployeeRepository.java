package com.hibernate.hibernatedemo.repository;

import com.hibernate.hibernatedemo.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class EmployeeRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;

    //Insert an employee
    public void insertEmployee(Employee employee){
        entityManager.persist(employee);

    }


    //Retrieve all employees
//  public List<Employee> retrieveAllEmployee(){
//       return entityManager.createQuery("Select e from Employee e", Employee.class).getResultList();   cannot use this while using superclass
    public List<PartimeEmployee> retrieveAllPartTimeEmployees(){
        return entityManager.createQuery("Select e from PartimeEmployee e", PartimeEmployee.class).getResultList();
    }

    public List<FulltimeEmployee> retrieveAllFullTimeEmployees(){
        return entityManager.createQuery("Select e from FulltimeEmployee e", FulltimeEmployee.class).getResultList();
    }


    public Course findById(Long id){
        return entityManager.find(Course.class, id);
    }


}


