package com.example.demo.services;

import com.example.demo.dtos.course.input.*;
import com.example.demo.dtos.course.output.*;
import com.example.demo.entitys.*;
import com.example.demo.exceptions.*;
import com.example.demo.repositories.*;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepo courseRepo;
    private final ObjectMapper objectMapper;

    public CourseDto createCourse(CreateCourseDto createCourseDto) {
        Course course = objectMapper.convertValue(createCourseDto, Course.class);
        Course savedCourse = courseRepo.save(course);
        return convertToDto(savedCourse);
    }

    public CourseDto updateCourse(Long courseId, UpdateCourseDto updateCourseDto) throws JsonMappingException {
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new NotFoundExep("Course not found with id: " + courseId));
        objectMapper.updateValue(course, updateCourseDto);
        Course updatedCourse = courseRepo.save(course);
        return convertToDto(updatedCourse);
    }

    public CourseDto getCourse(Long courseId) {
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new NotFoundExep("Course not found with id: " + courseId));
        return convertToDto(course);
    }

    public List<CourseDto> getAllCourses() {
        List<Course> courses = (List<Course>) courseRepo.findAll();
        return courses.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public void deleteCourse(Long courseId) {
        if (!courseRepo.existsById(courseId)) {
            throw new NotFoundExep("Course not found with id: " + courseId);
        }
        courseRepo.deleteById(courseId);
    }

    private CourseDto convertToDto(Course course) {
        return objectMapper.convertValue(course, CourseDto.class);
    }
}
