package com.example.moviesapi.controller;

import com.example.moviesapi.dto.LoginDTO;
import com.example.moviesapi.facade.UserFacade;
import com.example.moviesapi.model.entity.User;
import com.example.moviesapi.util.HandleToken;
import com.example.moviesapi.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("user")
public class UserController {

  private final UserFacade userFacade;
  private final AuthenticationManager authenticationManager;
  private final HandleToken handleToken;

  UserController(UserFacade userFacade, AuthenticationManager authenticationManager, HandleToken handleToken) {
    this.userFacade = userFacade;
    this.authenticationManager = authenticationManager;
    this.handleToken = handleToken;
  }

  @GetMapping("/list")
  public ResponseEntity<?> getAllUsers() {
    try {
      return ResponseEntity.ok(ResponseUtil.getResult(
          "Dados encontrados",
          200,
          userFacade.getAllUsers()));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
          .body(ResponseUtil.getResult(e.getMessage(), 422, null));
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getUserById(@PathVariable Long id) {
    try {
      return ResponseEntity.ok(ResponseUtil.getResult(
          "Usuário encontrado",
          200,
          userFacade.getUserById(id)));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(ResponseUtil.getResult(e.getMessage(), 404, null));
    }
  }

  @PostMapping("/save")
  public ResponseEntity<?> registerUser(@RequestBody User user) {
    try {
      return ResponseEntity.status(HttpStatus.CREATED)
          .body(ResponseUtil.getResult(
              "Usuário criado com sucesso",
              201,
              userFacade.registerUser(user)));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
          ResponseUtil.getResult(e.getMessage(), 400, null));
    }
  }

  @PutMapping("/update")
  public ResponseEntity<?> updateUser(@RequestBody User user) {
    try {
      return ResponseEntity.ok(ResponseUtil.getResult(
          "Os dados do usuário foram atualizados",
          200,
          userFacade.updateUser(user)));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
          ResponseUtil.getResult(e.getMessage(), 400, null));
    }
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteUser(@PathVariable Long id) {
    try {
      return ResponseEntity.ok(ResponseUtil.getResult(
          userFacade.deleteUser(id),
          200,
          null));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
          ResponseUtil.getResult(e.getMessage(), 400, null));
    }
  }

  @PutMapping("/{user_id}/add_bookmark/{movie_id}")
  public ResponseEntity<?> addFavoriteMovie(
      @PathVariable Long user_id,
      @PathVariable Long movie_id) {
    try {
      return ResponseEntity.ok(ResponseUtil.getResult(
          userFacade.addFavoriteMovie(user_id, movie_id),
          201,
          null));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
          ResponseUtil.getResult(e.getMessage(), 400, null));
    }
  }

  @PutMapping("/{user_id}/remove_bookmark/{movie_id}")
  public ResponseEntity<?> removeFavoriteMovie(
      @PathVariable Long user_id,
      @PathVariable Long movie_id) {
    try {
      return ResponseEntity.ok(ResponseUtil.getResult(
          userFacade.removeFavoriteMovie(user_id, movie_id),
          202,
          null));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
          ResponseUtil.getResult(e.getMessage(), 400, null));
    }
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginDTO userCredentials) {
    String username = userCredentials.username();
    String password = userCredentials.password();
    var userAndPass = new UsernamePasswordAuthenticationToken(username, password);

    try {
      Authentication auth = authenticationManager.authenticate(userAndPass);
      User user = (User) auth.getPrincipal();
      String token = handleToken.generateToken(user);
      return ResponseEntity.ok(ResponseUtil.getResult(
          "Usuário e senha validados com sucesso",
          200,
          token));

    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body(ResponseUtil.getResult(
              "Usuário e/ou senha inválidos",
              403,
              null));
    }
  }
}
