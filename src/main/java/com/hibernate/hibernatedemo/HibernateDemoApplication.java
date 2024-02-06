package com.hibernate.hibernatedemo;

import com.hibernate.hibernatedemo.entity.Course;
import com.hibernate.hibernatedemo.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HibernateDemoApplication implements CommandLineRunner {

	@Autowired
	private CourseRepository repository;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(HibernateDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Course course = repository.findById(10001L);

		logger.info("Course 10001 -> {}",course.toString());

		repository.save(new Course("Microservices Tutorial"));
//		repository.save();
	}
}
