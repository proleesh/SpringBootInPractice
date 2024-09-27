package org.proleesh.dto;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class AuthorCourseDto {
    private long id;
    private String authorName;
    private String courseName;
    private String description;
}
