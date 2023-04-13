package com.example.demo.services;

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

    private StudentDto convertToDto(Student student) {
        return objectMapper.convertValue(student, StudentDto.class);
    }
}
