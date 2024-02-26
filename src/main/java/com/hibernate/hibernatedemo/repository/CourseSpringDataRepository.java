package com.hibernate.hibernatedemo.repository;

import com.hibernate.hibernatedemo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "courses") //exposes this resource using spring jpa rest, this is the path now
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> { //tell it what to manage, and what the ID type is

    //findBy means select xyz

    List<Course> findByName(String name); //defining the method in the interface
    List<Course> countByName(String name);
    List<Course> findByNameOrderByIdDesc(String name);
    List<Course> deleteByName(String name);

    @Query("Select c From Course c where name like 'Maxine%'")
    List<Course> courseWithMaxineInName();

    @Query(value = "Select c From Course c where name like 'Maxine%'")
    List<Course> courseWithMaxineInNameUsingNativeQuery();

//    @Query(name = "query_get_tutorial")
//    List<Course> courseWithTutorialUsingNamedQuery();
}
