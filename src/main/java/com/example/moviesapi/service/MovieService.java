package com.example.moviesapi.service;

import java.util.List;
import java.util.Optional;
import com.example.moviesapi.model.entity.Actor;
import com.example.moviesapi.model.entity.Franchise;
import com.example.moviesapi.model.entity.Movie;
import com.example.moviesapi.model.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

  @Autowired
  private MovieRepository movieRepository;
  @Autowired
  private FranchiseService franchiseService;
  @Autowired
  private ActorService actorService;

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

  public Movie associateMovieToFranchise(Long movie_id, Long franchise_id) {
    Movie movie = getMovieById(movie_id).get();
    Franchise franchise = franchiseService.getFranchiseById(franchise_id);
    movie.setFranchise(franchise);
    Movie result = movieRepository.save(movie);
    return result;
  }

  public String addActorToMovie(Long movie_id, Long actor_id) {
    Movie movie = getMovieById(movie_id)
        .orElseThrow(() -> new IllegalArgumentException("Filme não encontrado"));
    Actor actor = actorService.getActorById(actor_id);

    List<Actor> actorList = movie.getActors();
    actorList.add(actor);
    movie.setActors(actorList);
    movieRepository.save(movie);

    String result = String.format(
        "O ator '%s' foi incluído no filme '%s'",
        actor.getName(), movie.getTitle());

    return result;
  }
}
