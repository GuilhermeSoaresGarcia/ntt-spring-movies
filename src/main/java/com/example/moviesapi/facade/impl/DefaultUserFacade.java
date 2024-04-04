package com.example.moviesapi.facade.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.moviesapi.dto.UserDTO;
import com.example.moviesapi.facade.UserFacade;
import com.example.moviesapi.model.entity.Movie;
import com.example.moviesapi.model.entity.User;
import com.example.moviesapi.service.MovieService;
import com.example.moviesapi.service.UserMapperService;
import com.example.moviesapi.service.UserService;

@Service
public class DefaultUserFacade implements UserFacade {

  @Autowired
  UserService userService;

  @Autowired
  MovieService movieService;

  @Override
  public List<UserDTO> getAllUsers() {
    List<User> usersList = userService.getAllUsers();
    if (CollectionUtils.isEmpty(usersList)) {
      throw new RuntimeException("Nenhum usuário cadastrado ainda.");
    }
    return UserMapperService.mapUsersToUserDTOs(usersList);
  }

  @Override
  public UserDTO getUserById(Long id) {
    Optional<User> optionalUser = userService.getUserById(id);
    if (optionalUser.isEmpty()) {
      throw new RuntimeException("Nenhum usuário encontrado");
    }
    User user = optionalUser.get();
    return UserMapperService.mapUserToUserDTO(user);
  }

  @Override
  public UserDTO registerUser(User user) {
    if (user.getId() != null) {
      throw new RuntimeException("O ID não deve ser informado");
    }
    return UserMapperService.mapUserToUserDTO(userService.saveUser(user));
  }

  @Override
  public UserDTO updateUser(User user) {
    if (user.getId() == null) {
      throw new RuntimeException("ID deve ser informado");
    }

    Optional<User> optionalUser = userService.getUserById(user.getId());

    if (optionalUser.isEmpty()) {
      throw new RuntimeException("Usuário não encontrado");
    }

    User userToUpdate = optionalUser.get();
    userToUpdate.setName(user.getName());
    userToUpdate.setEmail(user.getEmail());
    userToUpdate.setUsername(user.getUsername());
    userToUpdate.setPassword(user.getPassword());
    User savedUser = userService.saveUser(userToUpdate);

    return UserMapperService.mapUserToUserDTO(savedUser);
  }

  @Override
  public String deleteUser(Long id) {
    UserDTO userToBeDeleted = getUserById(id);    
    String userName = userToBeDeleted.getName();
    userService.deleteUser(id);
    return "O usuário '" + userName + "' de ID " + id + " foi excluído com sucesso!";
  }

  @Override
  public String addFavoriteMovie(
      Long user_id,
      Long movie_id) {

    Optional<User> optionalUser = userService.getUserById(user_id);
    if (optionalUser.isEmpty()) {
      throw new RuntimeException("Não foi possível localizar um usuário com o ID " + user_id);
    }
    User user = optionalUser.get();

    Optional<Movie> optionalMovie = movieService.getMovieById(movie_id);
    if (optionalMovie.isEmpty()) {
      throw new RuntimeException("Não foi possível localizar um filme com o ID " + movie_id);
    }
    Movie movie = optionalMovie.get();

    List<Movie> favoritesList = user.getFavorites();
    favoritesList.add(movie);
    user.setFavorites(favoritesList);
    userService.saveUser(user);

    return String.format(
        "Filme '%s' adicionado aos favoritos de '%s'",
        movie.getTitle(), user.getUsername());
  }

  @Override
  public String removeFavoriteMovie(
      Long user_id,
      Long movie_id) {

    Optional<User> optionalUser = userService.getUserById(user_id);
    if (optionalUser.isEmpty()) {
      throw new RuntimeException("Não foi possível localizar um usuário com o ID " + user_id);
    }
    User user = optionalUser.get();

    Optional<Movie> optionalMovie = movieService.getMovieById(movie_id);
    if (optionalMovie.isEmpty()) {
      throw new RuntimeException("Não foi possível localizar um filme com o ID " + movie_id);
    }
    Movie movie = optionalMovie.get();

    user.getFavorites().remove(movie);
    userService.saveUser(user);
    return String.format(
        "Filme '%s' removido dos favoritos de '%s'",
        movie.getTitle(), user.getUsername());
  }
}