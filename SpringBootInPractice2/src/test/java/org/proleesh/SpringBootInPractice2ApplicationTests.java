package org.proleesh;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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


}
