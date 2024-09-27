package org.proleesh;

import org.junit.jupiter.api.Test;
import org.proleesh.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpringBootInPractice6ApplicationTests {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void contextLoads() {
        assertThat(authorRepository.getAuthorCourseInfo(2)).hasSize(3);
    }

}
