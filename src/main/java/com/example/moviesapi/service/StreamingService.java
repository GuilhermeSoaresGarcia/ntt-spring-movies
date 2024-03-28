package com.example.moviesapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.moviesapi.model.entity.Streaming;
import com.example.moviesapi.model.repository.StreamingRepository;

@Service
public class StreamingService {

  @Autowired
  StreamingRepository streamingRepository;

  public List<Streaming> getAllStreamings() {
    return streamingRepository.findAll();
  }

  public Streaming getStreamingById(Long id) {
    if (id == null) {
      return null;
    }

    Optional<Streaming> optionalStreaming = streamingRepository.findById(id);

    if (optionalStreaming.isEmpty()) {
      return null;
    }

    return optionalStreaming.get();
  }

  public Streaming registerStreaming(Streaming streaming) {
    if (streaming.getId() != null) {
      return null;
    }
    return streamingRepository.save(streaming);
  }

  public Streaming updateStreaming(Streaming streaming) {
    if (streaming.getId() == null) {
      return null;
    }

    @SuppressWarnings("null")
    Streaming streamingToUpdate = streamingRepository.findById(streaming.getId()).get();
    streamingToUpdate.setName(streaming.getName());
    streamingToUpdate.setUrl(streaming.getUrl());
    Streaming result = streamingRepository.save(streamingToUpdate);
    return result;
  }

  public String deleteStreaming(Long id) {
    Streaming streamingToBeDeleted = getStreamingById(id);
    if (streamingToBeDeleted == null) {
      return "Não foi possível excluir pois nada foi encontrado com o ID " + id;
    }
    String streamingName = streamingToBeDeleted.getName();
    streamingRepository.deleteById(id);
    return "O streaming '" + streamingName + "' de ID " + id + " foi excluído com sucesso!";
  }
}
