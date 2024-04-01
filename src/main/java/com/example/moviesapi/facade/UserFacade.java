package com.example.moviesapi.facade;

import java.util.List;

import com.example.moviesapi.dto.UserDTO;
import com.example.moviesapi.model.entity.User;

public interface UserFacade {
  List<UserDTO> getAllUsers();

  UserDTO getUserById(Long id);

  UserDTO registerUser(User user);

  UserDTO updateUser(User user);

  String deleteUser(Long id);

  String addFavoriteMovie(Long user_id, Long movie_id);

  String removeFavoriteMovie(Long user_id, Long movie_id);
}
