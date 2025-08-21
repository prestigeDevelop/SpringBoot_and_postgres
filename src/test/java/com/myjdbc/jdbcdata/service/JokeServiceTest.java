package com.myjdbc.jdbcdata.service;

import com.myjdbc.jdbcdata.entities.Joke;
import com.myjdbc.jdbcdata.enums.JokeType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

//@ActiveProfiles("test")
@SpringBootTest
class JokeServiceTest {
    @Autowired
    private JokeService jokeService;

    @MockBean
    private WebClient webClient;

    @MockBean
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @MockBean
    private WebClient.ResponseSpec responseSpec;

    @Test
    public void testGetRandomJokes_Success() {
        // 1. Mock the WebClient call chain
        Joke[] mockJokes = {new Joke(JokeType.DAD, "setup1", "punchline1", 1),
                new Joke(JokeType.DAD, "setup1", "punchline1", 2),
                new Joke(JokeType.DAD, "setup2", "punchline2", 3)};

        Mockito.when(webClient.get()).thenReturn(requestHeadersUriSpec);
        Mockito.when(requestHeadersUriSpec.uri(Mockito.anyString())).thenReturn(requestHeadersUriSpec);
        Mockito.when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
        Mockito.when(responseSpec.bodyToMono(Joke[].class)).thenReturn(Mono.just(mockJokes));

        // 2. Test the service
        Mono<Joke[]> result = jokeService.getRandomJokes();

        // 3. Verify:
        // - The aspect logs the method call (check your console)
        // - The service returns expected data
        StepVerifier.create(result)
                .expectNext(mockJokes)
                .verifyComplete();
    }

    @Test
    public void testGetRandomJokes_Error() {
        // Mock a failed API call
        Mockito.when(webClient.get()).thenReturn(requestHeadersUriSpec);
        Mockito.when(requestHeadersUriSpec.uri(Mockito.anyString())).thenReturn(requestHeadersUriSpec);
        Mockito.when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
        Mockito.when(responseSpec.bodyToMono(Joke[].class))
                .thenReturn(Mono.error(new RuntimeException("API failed")));

        // Verify error handling
        StepVerifier.create(jokeService.getRandomJokes())
                .expectError(RuntimeException.class)
                .verify();
    }
}