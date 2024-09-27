package org.proleesh.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity(name="COURSE")
@Table(name = "COURSES")
public class Course {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "RATING")
    private int rating;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany(mappedBy="courses")
    private Set<Author> authors = new HashSet<>();

    public Course(String name, String category, int rating, String description) {
        this.name = name;
        this.category = category;
        this.rating = rating;
        this.description = description;
    }
}
