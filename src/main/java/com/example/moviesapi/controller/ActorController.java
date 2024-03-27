package com.example.moviesapi.controller;

import com.example.moviesapi.model.entity.Actor;
import com.example.moviesapi.service.ActorService;

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
@RequestMapping("actor")
public class ActorController {

  @Autowired
  ActorService actorService;

  @GetMapping("/list")
  public List<Actor> getAllActors() {
    return actorService.getAllActors();
  }

  @GetMapping("/{id}")
  public Actor getActorById(@PathVariable Long id) {
    return actorService.getActorById(id);
  }

  @PostMapping("/save")
  public Actor registerActor(@RequestBody Actor actor) {
    return actorService.registerActor(actor);
  }

  @PutMapping("/update")
  public Actor updateActor(@RequestBody Actor actor) {
    return actorService.updateActor(actor);
  }

  @DeleteMapping("/delete/{id}")
  public String deleteActor(@PathVariable Long id) {
    return actorService.deleteActor(id);
  }
}
