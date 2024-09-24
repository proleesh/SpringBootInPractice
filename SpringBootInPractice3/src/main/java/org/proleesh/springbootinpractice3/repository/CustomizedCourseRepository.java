package org.proleesh.springbootinpractice3.repository;

import org.proleesh.springbootinpractice3.entity.Course;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomizedCourseRepository extends BaseRepository<Course, Long>{
}
