package com.example.moviesapi.controller;

import com.example.moviesapi.model.entity.Movie;
import com.example.moviesapi.service.MovieService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
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
  public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
    try {
      return ResponseEntity.ok(movieService.getMovieById(id).get());
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  @GetMapping
  public ResponseEntity<List<Movie>> getMovieListByTitle(@RequestParam(name = "title") String title) {
    try {
      List<Movie> movieList = movieService.getMovieListByTitle(title);
      return ResponseEntity.ok(movieList);
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  @PostMapping("/save")
  public ResponseEntity<Movie> registerMovie(@RequestBody Movie movie) {
    try {
      return ResponseEntity.ok(movieService.registerMovie(movie));
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @PutMapping("/update")
  public Movie updateMovie(@RequestBody Movie movie) {
    try {
      Movie result = movieService.updateMovie(movie);
      return result;
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @DeleteMapping("delete/{id}")
  public String deleteMovie(@NotNull @Valid @PathVariable Long id) {
    try {
      return movieService.deleteMovie(id);
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @PutMapping("{movie_id}/franchise/{franchise_id}")
  public Movie associateMovieToFranchise(
      @NotNull @Valid @PathVariable Long movie_id,
      @NotNull @Valid @PathVariable Long franchise_id) {
    try {
      Movie result = movieService.associateMovieToFranchise(movie_id, franchise_id);
      return result;
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  @PutMapping("{movie_id}/add_actor/{actor_id}")
  public ResponseEntity<String> addActorToMovie(
      @NotNull @Valid @PathVariable Long movie_id,
      @NotNull @Valid @PathVariable Long actor_id) {
    try {
      String result = movieService.addActorToMovie(movie_id, actor_id);
      return ResponseEntity.ok(result);
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  @PutMapping("{movie_id}/add_director/{director_id}")
  public ResponseEntity<String> addDirectorToMovie(
      @NotNull @Valid @PathVariable Long movie_id,
      @NotNull @Valid @PathVariable Long director_id) {
    try {
      String result = movieService.addDirectorToMovie(movie_id, director_id);
      return ResponseEntity.ok(result);
    } catch (Exception e) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, e.getMessage());
    }
  }
}
