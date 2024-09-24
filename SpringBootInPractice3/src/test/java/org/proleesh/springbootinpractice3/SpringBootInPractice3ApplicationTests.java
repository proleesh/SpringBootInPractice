package org.proleesh.springbootinpractice3;

import org.junit.jupiter.api.Test;
import org.proleesh.springbootinpractice3.entity.Course;
import org.proleesh.springbootinpractice3.repository.CustomizedCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpringBootInPractice3ApplicationTests {

    @Autowired
    private CustomizedCourseRepository customizedCourseRepository;

    @Test
    void contextLoads() {
        Course course = new Course("Repid Spring Boot Application Development", "Sping",
                4, "The description");
        customizedCourseRepository.save(course);
        assertThat(Arrays.asList(customizedCourseRepository.findAll()).size()).isEqualTo(1);
    }

}
