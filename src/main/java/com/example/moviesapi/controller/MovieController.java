package com.example.moviesapi.controller;

import com.example.moviesapi.model.entity.Movie;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("movie")
public class MovieController {

  @Autowired
  private MovieService movieService;

  @GetMapping("/list")
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

  @GetMapping
  public ResponseEntity<List<Movie>> getMovieListByTitle(@RequestParam(name = "title") String title) {
    List<Movie> movieList = movieService.getMovieListByTitle(title);
    return ResponseEntity.ok(movieList);
  }

  @PostMapping("/save")
  public ResponseEntity<Movie> registerMovie(@RequestBody Movie movie) {
    Movie result = movieService.registerMovie(movie);
    return ResponseEntity.ok(result);
  }

  @PutMapping("/update")
  public Movie updateMovie(@RequestBody Movie movie) {
    Movie result = movieService.updateMovie(movie);
    return result;
  }

  @DeleteMapping("delete/{id}")
  public String deleteMovie(@PathVariable Long id) {
    movieService.deleteMovie(id);
    return "Deleted";
  }

  @PutMapping("{movie_id}/franchise/{franchise_id}")
  public Movie associateMovieToFranchise(
      @PathVariable Long movie_id,
      @PathVariable Long franchise_id) {
    Movie result = movieService.associateMovieToFranchise(movie_id, franchise_id);
    return result;
  }

}
