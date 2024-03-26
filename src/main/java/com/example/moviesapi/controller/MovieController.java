package com.example.moviesapi.controller;

import com.example.moviesapi.model.entity.Movie;
import com.example.moviesapi.model.repository.MovieRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

  private MovieRepository movieRepository;

  @Autowired
  public MovieController(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  @GetMapping
  public List<Movie> getAllMovies() {
    return movieRepository.findAll();
  }

  @GetMapping("/{id}")
  public Movie getMovieById(@PathVariable Long id) {
    return movieRepository.findById(id).get();
  }

  @PostMapping
  public Movie registerMovie(@RequestBody Movie movie) {
    Movie result = movieRepository.save(movie);
    return result;
  }

  @PutMapping("/{id}")
  public Movie updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
    Movie movieToUpdate = movieRepository.findById(id).get();
    movieToUpdate.setTitle(movie.getTitle());
    movieToUpdate.setReleaseDate(movie.getReleaseDate());
    Movie result = movieRepository.save(movieToUpdate);
    return result;
  }

  @DeleteMapping("/{id}")
  public String deleteMovie(@PathVariable Long id) {
    movieRepository.deleteById(id);
    return "Deleted";
  }
}
