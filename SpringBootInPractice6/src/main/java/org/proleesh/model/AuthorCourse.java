package org.proleesh.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name="AUTHOR_COURSE")
@Table(name="AUTHORS_COURSES")
public class AuthorCourse {
    @Id
    @Column(name="author_id")
    private long authorId;

    @Column(name="course_id")
    private long courseId;
}
