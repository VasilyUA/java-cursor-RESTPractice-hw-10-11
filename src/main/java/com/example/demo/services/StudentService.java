package com.example.demo.services;

import com.example.demo.dtos.course.output.*;
import com.example.demo.dtos.student.input.*;
import com.example.demo.dtos.student.output.*;
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
public class StudentService {

    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;
    private final ObjectMapper objectMapper;

    public StudentDto createStudent(CreateStudentDto createStudentDto) {
        Student student = objectMapper.convertValue(createStudentDto, Student.class);
        Student savedStudent = studentRepo.save(student);
        return convertToDto(savedStudent);
    }

    public StudentDto updateStudent(Long studentId, UpdateStudentDto updateStudentDto) throws JsonMappingException {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new NotFoundExep("Student not found with id: " + studentId));
        objectMapper.updateValue(student, updateStudentDto);
        Student updatedStudent = studentRepo.save(student);
        return convertToDto(updatedStudent);
    }

    public StudentDto getStudent(Long studentId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new NotFoundExep("Student not found with id: " + studentId));
        return convertToDto(student);
    }

    public List<StudentDto> getAllStudents() {
        List<Student> students = (List<Student>) studentRepo.findAll();
        return students.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public void deleteStudent(Long studentId) {
        if (!studentRepo.existsById(studentId)) {
            throw new NotFoundExep("Student not found with id: " + studentId);
        }
        studentRepo.deleteById(studentId);
    }

    public StudentDto addCourseToStudent(Long studentId, Long courseId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new NotFoundExep("Student not found with id: " + studentId));
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new NotFoundExep("Course not found with id: " + courseId));

        student.enrollInCourse(course);
        studentRepo.save(student);
        return convertToDto(student);
    }


    public StudentDto removeCourseFromStudent(Long studentId, Long courseId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new NotFoundExep("Student not found with id: " + studentId));

        List<Course> coursesToRemove = student.getCourses().stream()
                .filter(course -> course.getId().equals(courseId))
                .peek(course -> course.getStudents().remove(student))
                .toList();

        if (coursesToRemove.isEmpty()) {
            throw new NotFoundExep("Course not found with id: " + courseId + " for student with id: " + studentId);
        }

        student.getCourses().removeAll(coursesToRemove);
        studentRepo.save(student);
        return convertToDto(student);
    }



    private StudentDto convertToDto(Student student) {
        var courses = student.getCourses();

        if (courses == null) {
            return objectMapper.convertValue(student, StudentDto.class);
        }

        List<CourseDto> courseDto = courses.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        student.setCourses(null);

        var studentDto = objectMapper.convertValue(student, StudentDto.class);
        studentDto.setCourses(courseDto);

        return studentDto;
    }

    private CourseDto convertToDto(Course course) {
        return objectMapper.convertValue(course, CourseDto.class);
    }
}
