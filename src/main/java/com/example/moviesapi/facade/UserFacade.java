package com.example.moviesapi.facade;

import java.util.List;

import com.example.moviesapi.model.entity.User;

public interface UserFacade {
  List<User> getAllUsers();

  User getUserById(Long id);

  User registerUser(User user);

  User updateUser(User user);

  String deleteUser(Long id);

  String addFavoriteMovie();

  String removeFavoriteMovie();
}
