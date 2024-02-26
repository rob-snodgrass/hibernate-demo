package com.hibernate.hibernatedemo;

import com.hibernate.hibernatedemo.entity.*;
import com.hibernate.hibernatedemo.repository.CourseRepository;
import com.hibernate.hibernatedemo.repository.EmployeeRepository;
import com.hibernate.hibernatedemo.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class HibernateDemoApplication implements CommandLineRunner {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(HibernateDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		Course course = courseRepository.findById(10001L);
		logger.info("Course 10001 -> {}",course.toString());
		repository.save(new Course("Microservices Tutorial"));
		repository.save();
		 */

		//studentRepository.saveStudentWithPassport();

		/*
		List<Review> reviews =  new ArrayList<>();
		reviews.add(new Review("Great and hands on", "5"));
		reviews.add(new Review("Fell asleep during lesson", "2"));
		courseRepository.addReviewToCourse(10003L, reviews);
		 */

		//studentRepository.insertStudentAndCourse();
		//studentRepository.insertStudentAndCourse(new Student("Jack"), new Course("Microservices Tutorial"));

		/*
		employeeRepository.insertEmployee(new FulltimeEmployee("Maxine", new BigDecimal("100000")));
		employeeRepository.insertEmployee(new PartimeEmployee("Jill",new BigDecimal("25")));
		//logger.info("All Employees -> {}", employeeRepository.retrieveAllEmployee()); can't use this using mapped super class
		logger.info("Part Time Employees -> {}", employeeRepository.retrieveAllPartTimeEmployees());
		logger.info("Full Time Employees -> {}", employeeRepository.retrieveAllFullTimeEmployees());

		 */

	}
}
