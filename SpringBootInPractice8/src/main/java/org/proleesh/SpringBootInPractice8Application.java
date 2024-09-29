package org.proleesh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@SpringBootApplication
public class SpringBootInPractice8Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootInPractice8Application.class, args);
        Flux<Integer> intFlux = Flux.just(1, 2, 3);
        Flux<Integer> intFluxRange = Flux.range(1, 10);
        Flux<String> stringFlux = Flux.fromIterable(Arrays.asList("a", "b", "c"));
        Flux<String> anotherStringFlux = Flux.fromArray(new String[]{"d", "e", "f"});

        Mono<Integer> emptyMono = Mono.empty();
        Mono<Integer> intMono = Mono.just(1);
        intFlux.map(i -> i * 2).subscribe(System.out::println);

        System.out.println(intFlux);
        System.out.println(intFluxRange);
    }

}
