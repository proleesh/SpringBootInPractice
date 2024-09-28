package org.proleesh.springbootinpractice7.repository;

import org.proleesh.springbootinpractice7.model.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

}