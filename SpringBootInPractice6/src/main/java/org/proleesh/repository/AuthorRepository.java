package org.proleesh.repository;

import org.proleesh.dto.AuthorCourseDto;
import org.proleesh.model.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    @Query("SELECT new org.proleesh.dto.AuthorCourseDto(c.id, a.name, c.name, c.description)" + " from AUTHOR a, COURSE c, AUTHOR_COURSE ac where a.id = ac.authorId and c.id=ac.courseId and ac.authorId=?1")
    Iterable<AuthorCourseDto> getAuthorCourseInfo(long authorId);
}
