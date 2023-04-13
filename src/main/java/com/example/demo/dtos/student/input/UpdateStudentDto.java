package com.example.demo.dtos.student.input;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class UpdateStudentDto {

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, max = 100, message = "Password should be between 6 and 100 characters")
    private String password;
}
