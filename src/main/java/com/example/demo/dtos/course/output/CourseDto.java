package com.example.demo.dtos.course.output;

import lombok.*;

@Getter
@Setter
public class CourseDto {
    private Long id;
    private String name;
    private String description;
    private String category;
    private String price;
}
