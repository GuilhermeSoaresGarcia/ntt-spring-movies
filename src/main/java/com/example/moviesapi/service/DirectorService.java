package com.example.moviesapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.moviesapi.model.entity.Director;
import com.example.moviesapi.model.repository.DirectorRepository;

@Service
public class DirectorService {

  @Autowired
  DirectorRepository directorRepository;

  public List<Director> getAllDirectors() {
    return directorRepository.findAll();
  }

  public Director getDirectorById(Long id) {
    if (id == null) {
      return null;
    }

    Optional<Director> optionalDirector = directorRepository.findById(id);

    if (optionalDirector.isEmpty()) {
      return null;
    }

    return optionalDirector.get();
  }

  public Director registerDirector(Director director) {
    if (director.getId() != null) {
      return null;
    }
    return directorRepository.save(director);
  }

  public Director updateDirector(Director director) {
    if (director.getId() == null) {
      return null;
    }

    @SuppressWarnings("null")
    Director directorToUpdate = directorRepository.findById(director.getId()).get();
    directorToUpdate.setName(director.getName());
    Director result = directorRepository.save(directorToUpdate);
    return result;
  }

  public String deleteDirector(Long id) {
    Director directorToBeDeleted = getDirectorById(id);
    if (directorToBeDeleted == null) {
      return "Não foi possível excluir pois nada foi encontrado com o ID " + id;
    }
    String directorName = directorToBeDeleted.getName();
    directorRepository.deleteById(id);
    return "O diretor '" + directorName + "' de ID " + id + " foi excluído com sucesso!";
  }
}