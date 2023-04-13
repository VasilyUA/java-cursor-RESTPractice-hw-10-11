package com.example.demo.repositories;

import com.example.demo.entitys.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepo extends CrudRepository<Course, Long> {
}
