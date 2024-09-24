package org.proleesh;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.proleesh.entity.Course;
import org.proleesh.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
//
//@DataMongoTest
//@ExtendWith(SpringExtension.class)
@SpringBootTest
class SpringBootInPractice2ApplicationTests {
//    @Autowired
//    private MongoTemplate mongoTemplate;

    @Autowired
    private DataSource dataSource;

//    @Test
//    public void givenObjectAvailableWhenSaveToCollectionThenExpectValue(){
//        // given
//        DBObject object = BasicDBObjectBuilder.start()
//                .add("Manning", "Spring Boot In Practice").get();
//        // when
//        mongoTemplate.save(object, "collection");
//
//        // then
//        assertThat(mongoTemplate.findAll(DBObject.class, "collection"))
//                .extracting("Manning")
//                .containsOnly("Spring Boot In Practice");
//    }

    @Test
    public void whenCountCoursesThenExpectFiveCourses() throws SQLException {
        ResultSet rs = null;
        int noOfCourses = 0;

        try(PreparedStatement ps = dataSource.getConnection()
                .prepareStatement("select COUNT(1) from COURSES")){
            rs = ps.executeQuery();
            while(rs.next()){
                noOfCourses = rs.getInt(1);
            }
            assertThat(noOfCourses).isEqualTo(5L);
        }finally{
            if(rs != null){
                rs.close();
            }
        }
    }

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void givenCreateCourseWhenLoadTheCourseThenExpectSameCourse(){
        Course course = new Course("Rapid Spring Boot Application Development",
                "Spring", 4, "Spring Boot gives all the power of the Spring Framework without all of the complexities");

        Course savedCourse = courseRepository.save(course);

        assertThat(courseRepository.findById(savedCourse.getId()).get()).isEqualTo(course);
    }

    @Test
    public void givenUpdateCourseWhenLoadTheCourseThenExpectUpdatedCourse(){
        Course course = new Course("Rapid Spring Boot Application Development",
                "Spring", 4, "Spring Boot gives all the power of the Spring Framework without all of the complexities");
        courseRepository.save(course);
        course.setRating(5);
        Course savedCourse = courseRepository.save(course);
        assertThat(courseRepository.findById(savedCourse.getId()).get().getRating()).isEqualTo(5);
    }

}
