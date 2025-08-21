package com.myjdbc.jdbcdata.service;

import com.myjdbc.jdbcdata.entities.Joke;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@Slf4j
public class JokeService {
    @Value("${joke.api.url:https://official-joke-api.appspot.com}") // Default value if not set
    private final String jokeApiUrl;
    private final WebClient webClient;

    @Autowired
    public JokeService(WebClient webClient,
                       @Value("${joke.api.url:https://official-joke-api.appspot.com}") String jokeApiUrl) {
        this.webClient = webClient;
        this.jokeApiUrl = jokeApiUrl;
    }

    public Mono<Joke[]> getRandomJokes() {
        Mono<Joke[]> jokesList = webClient.get()
                .uri(jokeApiUrl + "/random_ten")
                .retrieve()
                .bodyToMono(Joke[].class)
                .timeout(Duration.ofSeconds(5))// Optional: Add timeout
                .switchIfEmpty(Mono.error(new RuntimeException("No jokes found")));
        return jokesList;
    }
}
