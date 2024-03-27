package com.example.moviesapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.moviesapi.model.entity.Actor;
import com.example.moviesapi.model.repository.ActorRepository;

@Service
public class ActorService {

  @Autowired
  ActorRepository actorRepository;

  public List<Actor> getAllActors() {
    return actorRepository.findAll();
  }

  public Actor getActorById(Long id) {
    if (id == null) {
      return null;
    }

    Optional<Actor> optionalActor = actorRepository.findById(id);

    if (optionalActor.isEmpty()) {
      return null;
    }

    return optionalActor.get();
  }

  public Actor registerActor(Actor actor) {
    if (actor.getId() != null) {
      return null;
    }
    return actorRepository.save(actor);
  }

  public Actor updateActor(Actor actor) {
    if (actor.getId() == null) {
      return null;
    }

    @SuppressWarnings("null")
    Actor actorToUpdate = actorRepository.findById(actor.getId()).get();
    actorToUpdate.setName(actor.getName());
    Actor result = actorRepository.save(actorToUpdate);
    return result;
  }

  public String deleteActor(Long id) {
    Actor actorToBeDeleted = getActorById(id);
    if (actorToBeDeleted == null) {
      return "Não foi possível excluir pois nada foi encontrado com o ID " + id;
    }
    String actorName = actorToBeDeleted.getName();
    actorRepository.deleteById(id);
    return "O ator/atriz '" + actorName + "' de ID " + id + " foi excluído com sucesso!";
  }
}