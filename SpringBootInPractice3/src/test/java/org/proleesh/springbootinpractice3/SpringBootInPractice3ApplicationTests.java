package org.proleesh.springbootinpractice3;

import org.junit.jupiter.api.Test;
import org.proleesh.springbootinpractice3.entity.Course;
import org.proleesh.springbootinpractice3.repository.CourseRepository;
//import org.proleesh.springbootinpractice3.repository.CustomizedCourseRepository;
//import org.proleesh.springbootinpractice3.repository.CustomizedCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpringBootInPractice3ApplicationTests {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void givenCreateCourseWhenLoadTheCourseThenExpectSameCourse(){
        // 과정 목록 저장
        courseRepository.saveAll(getCourseList());

        // Spring 카테고리의 코스는 3개 있어야 함
        assertThat(courseRepository.findAllByCategory("Spring")).hasSize(3);

        // Java 과정은 존재하지 않음 (assert 수정)
        assertThat(courseRepository.existsByName("Java")).isFalse();

        // JavaScript 과정은 존재함
        assertThat(courseRepository.existsByName("JavaScript")).isTrue();

        // Python 카테고리 과정은 없으므로 0개여야 함 (assert 수정)
        assertThat(courseRepository.countByCategory("Python")).isEqualTo(0);

        // "Getting Start"로 시작하는 이름의 코스는 0개여야 함 (테스트 데이터에는 없으므로)
        assertThat(courseRepository.findByNameStartsWith("Getting Start")).hasSize(0);
    }

    private List<Course> getCourseList(){
        Course course1 = new Course("Rapid Spring Boot Application Development", "Spring", 4, "Spring Boot gives all the power of the Spring Framework without all of the complexity");
        Course course2 = new Course("Learn Spring Boot Application Development", "Spring", 5, "Spring Boot gives all the power of the Spring Framework without all of the complexity");
        Course course3 = new Course("Master Spring Boot Application Development", "Spring", 3, "Spring Boot gives all the power of the Spring Framework without all of the complexity");
        Course course4 = new Course("JavaScript", "JavaScript", 3, "JavaScript gives all the power of the Spring Framework without all of the complexity");
        return Arrays.asList(course1, course2, course3, course4);
    }
}
