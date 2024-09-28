package org.proleesh.springbootinpractice7.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "COURSES")
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME")
    @NotEmpty(message = "Course name field can't be empty")
    private String name;

    @Column(name = "CATEGORY")
    @NotEmpty(message = "Course category field can't be empty")
    private String category;

    @Column(name = "RATING")
    @Min(value = 1)
    @Max(value = 5)
    private int rating;

    @Column(name = "DESCRIPTION")
    @NotEmpty(message = "Course description field can't be empty")
    private String description;
}