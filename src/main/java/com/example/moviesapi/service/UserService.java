package com.example.moviesapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.example.moviesapi.model.entity.User;
import com.example.moviesapi.model.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public Optional<User> getUserById(Long id) {
    return userRepository.findById(id);
  }

  public User saveUser(User user) {
    String userPass = user.getPassword();
    String salt = BCrypt.gensalt(12);
    String userEncriptedPass = BCrypt.hashpw(userPass, salt);
    user.setPassword(userEncriptedPass);

    return userRepository.save(user);
  }

  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

  public User addFavoriteMovie(User user) {
    return userRepository.save(user);
  }

  public User removeFavoriteMovie(User user) {
    return userRepository.save(user);
  }
}