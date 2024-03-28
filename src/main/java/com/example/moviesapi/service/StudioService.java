package com.example.moviesapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.moviesapi.model.entity.Studio;
import com.example.moviesapi.model.repository.StudioRepository;

@Service
public class StudioService {

  @Autowired
  StudioRepository studioRepository;

  public List<Studio> getAllStudios() {
    return studioRepository.findAll();
  }

  public Studio getStudioById(Long id) {
    if (id == null) {
      return null;
    }

    Optional<Studio> optionalStudio = studioRepository.findById(id);

    if (optionalStudio.isEmpty()) {
      return null;
    }

    return optionalStudio.get();
  }

  public Studio registerStudio(Studio studio) {
    if (studio.getId() != null) {
      return null;
    }
    return studioRepository.save(studio);
  }

  public Studio updateStudio(Studio studio) {
    if (studio.getId() == null) {
      return null;
    }

    Studio studioToUpdate = studioRepository.findById(studio.getId()).get();
    studioToUpdate.setName(studio.getName());
    studioToUpdate.setCountry(studio.getCountry());
    Studio result = studioRepository.save(studioToUpdate);
    return result;
  }

  public String deleteStudio(Long id) {
    Studio studioToBeDeleted = getStudioById(id);
    if (studioToBeDeleted == null) {
      return "Não foi possível excluir pois nada foi encontrado com o ID " + id;
    }
    String studioName = studioToBeDeleted.getName();
    studioRepository.deleteById(id);
    return "O estúdio '" + studioName + "' de ID " + id + " foi excluído com sucesso!";
  }
}
