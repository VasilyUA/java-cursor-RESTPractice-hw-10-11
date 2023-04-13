package com.example.demo.dtos.course.input;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class CreateCourseDto {

    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Description is mandatory")
    @Size(min = 10, max = 500, message = "Description should be between 10 and 500 characters")
    private String description;

    @NotBlank(message = "Category is mandatory")
    @Size(min = 2, max = 100, message = "Category should be between 2 and 100 characters")
    private String category;

    @NotNull(message = "Price is mandatory")
    private double price;
}
