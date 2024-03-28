package com.example.moviesapi.controller;

import com.example.moviesapi.model.entity.Director;
import com.example.moviesapi.service.DirectorService;

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
@RequestMapping("director")
public class DirectorController {

  @Autowired
  DirectorService directorService;

  @GetMapping("/list")
  public List<Director> getAllDirectors() {
    return directorService.getAllDirectors();
  }

  @GetMapping("/{id}")
  public Director getDirectorById(@PathVariable Long id) {
    return directorService.getDirectorById(id);
  }

  @PostMapping("/save")
  public Director registerDirector(@RequestBody Director director) {
    return directorService.registerDirector(director);
  }

  @PutMapping("/update")
  public Director updateDirector(@RequestBody Director director) {
    return directorService.updateDirector(director);
  }

  @DeleteMapping("/delete/{id}")
  public String deleteDirector(@PathVariable Long id) {
    return directorService.deleteDirector(id);
  }
}
