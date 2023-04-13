package com.example.demo.controllers;

import com.example.demo.dtos.student.input.*;
import com.example.demo.dtos.student.output.*;
import com.example.demo.exceptions.*;
import com.example.demo.services.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentsController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity createStudent(@Valid @RequestBody CreateStudentDto createStudentDto) {
        try {
            StudentDto studentDto = studentService.createStudent(createStudentDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(studentDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Internal server error\"}");
        }
    }

    @PutMapping("/{studentId}")
    public ResponseEntity updateStudent(@PathVariable Long studentId, @Valid @RequestBody UpdateStudentDto updateStudentDto) {
        try {
            StudentDto studentDto = studentService.updateStudent(studentId, updateStudentDto);
            return ResponseEntity.ok(studentDto);
        } catch (NotFoundExep e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Student not found with id: " + studentId + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Internal server error\"}");
        }
    }

    @GetMapping("/{studentId}")
    public ResponseEntity getStudent(@PathVariable Long studentId) {
        try {
            StudentDto studentDto = studentService.getStudent(studentId);
            return ResponseEntity.ok(studentDto);
        } catch (NotFoundExep e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Student not found with id: " + studentId + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Internal server error\"}");
        }
    }

    @GetMapping
    public ResponseEntity getAllStudents() {
        try {
            List<StudentDto> students = studentService.getAllStudents();
            return ResponseEntity.ok(students);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Internal server error\"}");
        }
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity deleteStudent(@PathVariable Long studentId) {
        try {
            studentService.deleteStudent(studentId);
            return ResponseEntity.noContent().build();
        } catch (NotFoundExep e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Student not found with id: " + studentId + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Internal server error\"}");
        }
    }
}
