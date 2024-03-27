package com.example.moviesapi.service;

import java.util.List;
import java.util.Optional;

import com.example.moviesapi.model.entity.Movie;
import com.example.moviesapi.model.repository.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

  @Autowired
  private MovieRepository movieRepository;

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

  public List<Movie> getMovieListByTitle(String title) {
    List<Movie> movieList = movieRepository.findMoviesByTitle(title);
    return movieList;
  }

  public Movie registerMovie(Movie movie) {
    if (movie == null) {
      return null;
    }
    Movie result = movieRepository.save(movie);
    return result;
  }

  public Movie updateMovie(Movie movie) {
    Movie movieToUpdate = movieRepository.findById(movie.getId()).get();
    movieToUpdate.setTitle(movie.getTitle());
    Movie result = movieRepository.save(movieToUpdate);
    return result;
  }

  @SuppressWarnings("null")
	public String deleteMovie(Long id) {
    movieRepository.deleteById(id);
    return "Deleted";
  }

}
