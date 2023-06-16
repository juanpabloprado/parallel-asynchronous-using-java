package com.learnjava.apiclient;

import com.learnjava.domain.movie.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.*;

class MoviesClientTest {

    WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080/movies").build();

    MoviesClient moviesClient = new MoviesClient(webClient);

    @Test
    void retrieveMovie() {
        Movie movie = moviesClient.retrieveMovie(1L);
        assertNotNull(movie);
        System.out.println("Movie: " + movie);
        assertEquals("Batman Begins", movie.getMovieInfo().getName());
        assertEquals(1, movie.getReviewList().size());
    }
}