package org.proleesh.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@ToString
@Entity(name = "AUTHOR")
@Table(name="AUTHORS")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String bio;

    @ManyToMany
    @JoinTable(name="authors_courses", joinColumns={@JoinColumn(name="author_id", referencedColumnName = "id", nullable=false, updatable = false, insertable = false)},
    inverseJoinColumns = {@JoinColumn(name="course_id", referencedColumnName = "id", nullable=false, updatable=false, insertable = false)})
    private Set<Course> courses = new HashSet<>();

    public Author(String name, String bio) {
        this.name = name;
        this.bio = bio;
    }
}
