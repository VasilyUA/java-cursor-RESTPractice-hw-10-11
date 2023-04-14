package com.example.demo.entitys;

import javax.persistence.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String category;
    private String price;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL)
    private List<Student> students;
}
