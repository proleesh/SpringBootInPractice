package org.proleesh;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.proleesh.model.Course;
import org.proleesh.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;


@SpringBootApplication
public class SpringBootInPracticeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootInPracticeApplication.class, args);
    }

    private static Logger logger = LoggerFactory.getLogger(SpringBootInPracticeApplication.class);
    @Override
    public void run(String... args) throws Exception {
//        Course course = new Course();
//        course.setId(1);
//        course.setRating(4);

//        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
//        Set<ConstraintViolation<Course>> violations = validator.validate(course);
//
//        violations.forEach(courseConstraintViolation -> logger.error("A constraint violation has occurred. Violation details:[{}].", courseConstraintViolation));


        User user1 = new User("proleesh01", "proleesh");

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user1);

        logger.error("Password for user1 do not adhere to the password policy");

        violations.forEach(constraintViolation -> logger.error("Violation details: [{}]", constraintViolation.getMessage()));

        User user2 = new User("sbip02", "Sbip01$3UDfg");
        violations = validator.validate(user2);
        if(violations.isEmpty()){
            logger.info("Password for user2 adhere to the password policy");
        }

        User user3 = new User("sbip03", "Sbip01$4UDFgggg");
        violations = validator.validate(user3);
        logger.error("Password for user3 violates maximum repetitive rule");
        violations.forEach(constraintViolation -> logger.error("Violation details: [{}]", constraintViolation.getMessage()));

        User user4 = new User("sbip04", "Sbip4UDFgggg");
        violations = validator.validate(user4);
        logger.error("Password for user4 violates special character rule");
        violations.forEach(constraintViolation -> logger.error("Violation details: [{}]", constraintViolation.getMessage()));
    }
}
