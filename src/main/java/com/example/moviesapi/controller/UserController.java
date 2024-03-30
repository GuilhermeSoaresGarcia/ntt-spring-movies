package com.example.moviesapi.controller;

import com.example.moviesapi.model.entity.User;
import com.example.moviesapi.service.UserService;

import io.swagger.v3.oas.annotations.Hidden;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@Hidden
@RequestMapping("user")
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping("/list")
  public ResponseEntity<List<User>> getAllUsers() {
    return ResponseEntity.ok(userService.getAllUsers());
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    return ResponseEntity.ok(userService.getUserById(id));
  }

  @PostMapping("/save")
  public ResponseEntity<User> registerUser(@RequestBody User user) {
    return ResponseEntity.ok(userService.registerUser(user));
  }

  @PutMapping("/update")
  public ResponseEntity<User> updateUser(@RequestBody User user) {
    return ResponseEntity.ok(userService.updateUser(user));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable Long id) {
    return ResponseEntity.ok(userService.deleteUser(id));
  }
}
