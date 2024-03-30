package com.example.moviesapi.controller;

import com.example.moviesapi.model.entity.Genre;
import com.example.moviesapi.service.GenreService;

import io.swagger.v3.oas.annotations.Hidden;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("genre")
public class GenreController {

  @Autowired
  GenreService genreService;

  @GetMapping("/list")
  public List<Genre> getAllGenres() {
    return genreService.getAllGenres();
  }

  @GetMapping("/{id}")
  public Genre getGenreById(@PathVariable Long id) {
    return genreService.getGenreById(id);
  }

  @PostMapping("/save")
  public Genre registerGenre(@RequestBody Genre genre) {
    return genreService.registerGenre(genre);      
  }

  @PutMapping("/update")
  public Genre updateGenre(@RequestBody Genre genre) {
      return genreService.updateGenre(genre);
  }

  @DeleteMapping("/delete/{id}")
  public String deleteGenre(@PathVariable Long id){
    return genreService.deleteGenre(id);
  }
}
