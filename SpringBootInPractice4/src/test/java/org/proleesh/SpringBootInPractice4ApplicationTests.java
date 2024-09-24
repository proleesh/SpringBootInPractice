package org.proleesh;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.proleesh.entity.Course;
import org.proleesh.entity.QCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class SpringBootInPractice4ApplicationTests {

	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private EntityManager entityManager;

	@Test
	void contextLoads() {
		courseRepository.saveAll(getCourseList());

		QCourse course = QCourse.course;
		JPAQuery query1 = new JPAQuery(entityManager);
		query1.from(course).where(course.category.eq("Spring"));
		assertThat(query1.fetch().size()).isEqualTo(3);

		JPAQuery query2 = new JPAQuery(entityManager);
		query2.from(course).where(course.category.eq("Spring").and(course.rating.gt(3)));
		assertThat(query2.fetch().size()).isEqualTo(2);

		OrderSpecifier<Integer> descOrderSpecifier = course.rating.desc();
		assertThat(Lists.newArrayList(courseRepository.findAll(descOrderSpecifier))
				.get(0).getName()).isEqualTo("Getting Started with Spring Security DSL");


	}

	private List<Course> getCourseList() {
		Course rapidSpringBootCourse = new Course("Rapid Spring Boot Application Development", "Spring", 4,
				"Spring boot gives all the power of the Spring Framework without all of the complexity");
		Course springSecurityDslCourse = new Course("Getting Started with Spring Security DSL","Spring", 5,
				"Learn Spring Security DSL in easy steps");
		Course springCloudKubernatesCourse = new Course("Getting Started with Spring Bloud Kubernetes", "Spring", 3,
				"Master Spring Boot application deployment with Kubernetes");
		Course rapidPythonCourse = new Course("Getting Started with Python", "Python", 5,
				"Learn Python concepts in easy steps");

		return Arrays.asList(rapidSpringBootCourse, springSecurityDslCourse, springCloudKubernatesCourse, rapidPythonCourse);
	}

}
