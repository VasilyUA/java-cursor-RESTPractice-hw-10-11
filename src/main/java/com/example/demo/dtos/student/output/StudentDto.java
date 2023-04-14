package com.example.demo.dtos.student.output;

import com.example.demo.dtos.course.output.CourseDto;
import lombok.Data;

import java.util.*;

@Data
public class StudentDto {
    private Long id;
    private String email;
    private List<CourseDto> courses;

    public StudentDto(Long id, List<CourseDto> collect) {
        this.id = id;
        this.courses = collect;
    }
}
