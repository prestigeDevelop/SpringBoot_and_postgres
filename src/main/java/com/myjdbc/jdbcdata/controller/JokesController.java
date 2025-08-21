package com.myjdbc.jdbcdata.controller;

import com.myjdbc.jdbcdata.entities.Joke;
import com.myjdbc.jdbcdata.service.JokeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/jokes")
@RequiredArgsConstructor
public class JokesController {

    private final JokeService jokeService;


    @GetMapping("/random")
    //generate swagger documentation
    @Operation(summary = "Get random jokes", description = "Fetches a list of random jokes from the external API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved jokes"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
            @ApiResponse(responseCode = "404", description = "No jokes found"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public Mono<Joke[]> getRandomJokes() {
        Mono<Joke[]> jokes = jokeService.getRandomJokes().
                switchIfEmpty(Mono.error(new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No jokes found")));
        return jokes;
    }
}
