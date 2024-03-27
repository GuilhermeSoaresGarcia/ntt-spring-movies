package com.example.moviesapi.service;

import java.util.List;
import java.util.Optional;

import com.example.moviesapi.model.entity.Movie;
import com.example.moviesapi.model.repository.MovieRepository;
import com.fasterxml.jackson.annotation.OptBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class MovieService {

  private MovieRepository movieRepository;

  @Autowired
  public MovieService(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  public List<Movie> getAllMovies() {
    return movieRepository.findAll();
  }

  public Optional<Movie> getMovieById(Long id) {
    @SuppressWarnings("null")
    Optional<Movie> optionalMovie = movieRepository.findById(id);
    if (optionalMovie.isEmpty()) {
      return optionalMovie;
    }
    return Optional.of(optionalMovie).get();
  }

  public Movie registerMovie(Movie movie) {
    if (movie.getId() != null) {
      return null;
    }
    Movie result = movieRepository.save(movie);
    return result;
  }

  public Movie updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
    Movie movieToUpdate = movieRepository.findById(id).get();
    movieToUpdate.setTitle(movie.getTitle());
    movieToUpdate.setReleaseDate(movie.getReleaseDate());
    Movie result = movieRepository.save(movieToUpdate);
    return result;
  }

  public String deleteMovie(@PathVariable Long id) {
    movieRepository.deleteById(id);
    return "Deleted";
  }

}
