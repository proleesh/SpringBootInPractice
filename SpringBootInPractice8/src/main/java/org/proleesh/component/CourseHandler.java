package org.proleesh.component;

import lombok.RequiredArgsConstructor;
import org.proleesh.model.Course;
import org.proleesh.repository.CourseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@RequiredArgsConstructor
@Component
public class CourseHandler {
    private final CourseRepository courseRepository;

    /**
     * 모든 과정을 조회하는 핸들러. ServerRequest는 클라이언트의 요청을 담고 있는 서버 측 HTTP 요청
     * 객체다. 모든 과정을 조회해서 Content-type이 application/json인 과정 목록을 응답 본문으로
     * ServerResponse에 담는다. ServerResponse는 서버 측 응답 객체다.
     * @param serverRequest
     * @return
     */
    public Mono<ServerResponse> findAllCourses(ServerRequest serverRequest) {
        Flux<Course> courses = this.courseRepository.findAll();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(courses, Course.class);
    }

    /**
     * ID로 한 개의 과정을 조회하는 핸들러. 경로 변수로 넘어온 ID 값으로 과정을 조회해서 있으면 HTTP 200 OK를, 없으면 HTTP 404 NOT FOUND를
     * ServerResponse에 담아 반환한다.
     * @param serverRequest
     * @return
     */
    public Mono<ServerResponse> findCourseById(ServerRequest serverRequest){
        String courseId = serverRequest.pathVariable("id");
        Mono<Course> courseMono = this.courseRepository.findById(courseId);
        return courseMono.flatMap(course -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON).body(fromValue(course)))
                .switchIfEmpty(notFound());
    }

    /**
     * 새 과정을 생성하는 핸들러. ServerRequest의 bodyToMono 메서드를 사용해서 HTTP 요청 본문을 추출하고 Mono로 변환한다. 이
     * Mono가 과정을 생성하는 데 사용된다.
     * @param serverRequest
     * @return
     */
    public Mono<ServerResponse> createCourse(ServerRequest serverRequest) {
        Mono<Course> courseMono = serverRequest.bodyToMono(Course.class);
        return courseMono.flatMap(course -> ServerResponse.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON)
                .body(this.courseRepository.save(course), Course.class));
    }


    public Mono<ServerResponse> updateCourse(ServerRequest serverRequest) {
        String courseId = serverRequest.pathVariable("id");
        Mono<Course> existingCourseMono = this.courseRepository.findById(courseId);
        Mono<Course> newCourseMono = serverRequest.bodyToMono(Course.class);
        return newCourseMono
                .zipWith(existingCourseMono, (newCourse, existingCourse) ->
                        Course.builder().id(existingCourse.getId())
                                .name(newCourse.getName()).category(newCourse.getCategory())
                                .rating(newCourse.getRating()).description(newCourse.getDescription()).build())
                .flatMap(course -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(this.courseRepository.save(course), Course.class)).switchIfEmpty(notFound());
    }

    public Mono<ServerResponse> deleteCourse(ServerRequest serverRequest) {
        String courseId = serverRequest.pathVariable("id");
        return this.courseRepository.findById(courseId)
                .flatMap(existingCourse -> ServerResponse.ok().build(this.courseRepository.deleteById(courseId)))
                .switchIfEmpty(notFound());
    }

    public Mono<ServerResponse> deleteAllCourses(ServerRequest serverRequest) {
        return ServerResponse.ok().build(this.courseRepository.deleteAll());
    }

    private Mono<ServerResponse> notFound() {
        return ServerResponse.notFound().build();
    }


}
