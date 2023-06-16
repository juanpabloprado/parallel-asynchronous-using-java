package com.learnjava.apiclient;

import com.learnjava.domain.movie.Review;
import com.learnjava.domain.movie.Movie;
import com.learnjava.domain.movie.MovieInfo;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public class MoviesClient {

    private final WebClient webClient;

    public MoviesClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Movie retrieveMovie(long movieInfoId) {
        // movieInfo
        MovieInfo movieInfo = invokeMovieInfo(movieInfoId);
        // review
        List<Review> reviews = invokeReview(movieInfoId);

        return new Movie(movieInfo, reviews);
    }

    private MovieInfo invokeMovieInfo(long movieInfoId) {
        String moviesInfoUrlPath = "/v1/movie_infos/{movieInfoId}";

        return webClient.get()
                .uri(moviesInfoUrlPath, movieInfoId)
                .retrieve()
                .bodyToMono(MovieInfo.class)
                .block();
    }

    private List<Review> invokeReview(long movieInfoId) {
        String reviewUri = UriComponentsBuilder.fromUriString("/v1/reviews")
                .queryParam("movieInfoId", movieInfoId)
                .buildAndExpand()
                .toString();

        return webClient.get()
                .uri(reviewUri)
                .retrieve()
                .bodyToFlux(Review.class)
                .collectList()
                .block();
    }
}
