package com.example.demo.controllers;

import com.example.demo.dtos.course.input.*;
import com.example.demo.dtos.course.output.*;
import com.example.demo.exceptions.*;
import com.example.demo.services.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CoursesController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity createCourse(@Valid @RequestBody CreateCourseDto createCourseDto) {
        try {
            CourseDto courseDto = courseService.createCourse(createCourseDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(courseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Internal server error\"}");
        }
    }

    @PutMapping("/{courseId}")
    public ResponseEntity updateCourse(@PathVariable Long courseId, @Valid @RequestBody UpdateCourseDto updateCourseDto) {
        try {
            CourseDto courseDto = courseService.updateCourse(courseId, updateCourseDto);
            return ResponseEntity.ok(courseDto);
        } catch (NotFoundExep e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Course not found" + courseId + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Internal server error\"}");
        }
    }

    @GetMapping("/{courseId}")
    public ResponseEntity getCourse(@PathVariable Long courseId) {
        try {
            CourseDto courseDto = courseService.getCourse(courseId);
            return ResponseEntity.ok(courseDto);
        } catch (NotFoundExep e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Course not found" + courseId + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Internal server error\"}");
        }
    }

    @GetMapping
    public ResponseEntity getAllCourses() {
        try {
            List<CourseDto> courses = courseService.getAllCourses();
            return ResponseEntity.ok(courses);
        } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Internal server error\"}");
        }
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity deleteCourse(@PathVariable Long courseId) {
        try {
            courseService.deleteCourse(courseId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("{\"message\": \"Course with id: " + courseId + " was deleted\"}");
        } catch (NotFoundExep e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Course not found" + courseId + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Internal server error\"}");
        }
    }
}
