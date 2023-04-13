package com.example.demo.repositories;

import com.example.demo.entitys.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepo extends CrudRepository<Student, Long> {

}
