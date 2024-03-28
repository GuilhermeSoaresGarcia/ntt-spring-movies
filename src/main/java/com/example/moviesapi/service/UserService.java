package com.example.moviesapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

  public User getUserById(Long id) {
    if (id == null) {
      return null;
    }

    Optional<User> optionalUser = userRepository.findById(id);

    if (optionalUser.isEmpty()) {
      return null;
    }

    return optionalUser.get();
  }

  public User registerUser(User user) {
    if (user.getId() != null) {
      return null;
    }
    return userRepository.save(user);
  }

  public User updateUser(User user) {
    if (user.getId() == null) {
      return null;
    }

    @SuppressWarnings("null")
    User userToUpdate = userRepository.findById(user.getId()).get();
    userToUpdate.setName(user.getName());
    userToUpdate.setEmail(user.getEmail());
    userToUpdate.setUsername(user.getUsername());
    userToUpdate.setPassword(user.getPassword());
    User result = userRepository.save(userToUpdate);
    return result;
  }

  public String deleteUser(Long id) {
    User userToBeDeleted = getUserById(id);
    if (userToBeDeleted == null) {
      return "Não foi possível excluir pois nada foi encontrado com o ID " + id;
    }
    String userName = userToBeDeleted.getName();
    userRepository.deleteById(id);
    return "O usuário '" + userName + "' de ID " + id + " foi excluído com sucesso!";
  }
}