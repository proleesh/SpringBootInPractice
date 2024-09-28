package org.proleesh.springbootinpractice7.service.impl;

import org.proleesh.springbootinpractice7.model.Course;
import org.proleesh.springbootinpractice7.repository.CourseRepository;
import org.proleesh.springbootinpractice7.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultCourseService implements CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public DefaultCourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Optional<Course> findCourseById(Long courseId) {
        return courseRepository.findById(courseId);
    }

    public Iterable<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourseById(Long courseId) {
        courseRepository.deleteById(courseId);
    }
}