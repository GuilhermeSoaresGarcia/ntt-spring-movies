package com.example.moviesapi.controller;

import com.example.moviesapi.model.entity.Streaming;
import com.example.moviesapi.service.StreamingService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("streaming")
public class StreamingController {

  @Autowired
  StreamingService streamingService;

  @GetMapping("/list")
  public ResponseEntity<List<Streaming>> getAllStreamings() {
    return ResponseEntity.ok(streamingService.getAllStreamings());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Streaming> getStreamingById(@PathVariable Long id) {
    return ResponseEntity.ok(streamingService.getStreamingById(id));
  }

  @PostMapping("/save")
  public ResponseEntity<Streaming> registerStreaming(@RequestBody Streaming streaming) {
    Streaming result = streamingService.registerStreaming(streaming);
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(result);
  }

  @PutMapping("/update")
  public ResponseEntity<Streaming> updateStreaming(@RequestBody Streaming streaming) {
    return ResponseEntity.ok(streamingService.updateStreaming(streaming));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteStreaming(@PathVariable Long id) {
    return ResponseEntity.ok(streamingService.deleteStreaming(id));
  }

  @PutMapping("{streaming_id}/add_movie/{movie_id}")
  public ResponseEntity<String> associateStreamingToMovie(
      @PathVariable Long streaming_id,
      @PathVariable Long movie_id) {
    String result = streamingService.associateStreamingToMovie(streaming_id, movie_id);
    return ResponseEntity.ok(result);
  }

  @PutMapping("{streaming_id}/remove_movie/{movie_id}")
  public ResponseEntity<String> removeMovieFromStreaming(
      @PathVariable Long streaming_id,
      @PathVariable Long movie_id) {
    String result = streamingService.removeMovieFromStreaming(streaming_id, movie_id);
    return ResponseEntity.ok(result);
  }
}
