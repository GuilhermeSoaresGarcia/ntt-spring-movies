package com.example.moviesapi.controller;

import com.example.moviesapi.model.entity.Movie;
import com.example.moviesapi.model.repository.MovieRepository;
import com.example.moviesapi.service.MovieService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("movies")
public class MovieController {

  private MovieService movieService;

  @Autowired
  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }

  @GetMapping
  public ResponseEntity<List<Movie>> getAllMovies() {
    return ResponseEntity.ok(movieService.getAllMovies());
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getMovieById(@PathVariable Long id) {
    Optional<Movie> optionalMovie = movieService.getMovieById(id);
    if (optionalMovie.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("Nenhum registro encontrado com este ID");
    }
    return ResponseEntity.ok(optionalMovie.get());
  }

  @PostMapping
  public Movie registerMovie(@RequestBody Movie movie) {
    Movie result = movieService.registerMovie(movie);
    return result;
  }

  @PutMapping("/{id}")
  public Movie updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
    Movie movieToUpdate = movieService.getMovieById(id);
    movieToUpdate.setTitle(movie.getTitle());
    movieToUpdate.setReleaseDate(movie.getReleaseDate());
    Movie result = movieService.updateMovie(id, movieToUpdate);
    return result;
  }

  @DeleteMapping("/{id}")
  public String deleteMovie(@PathVariable Long id) {
    movieService.deleteMovie(id);
    return "Deleted";
  }
}
