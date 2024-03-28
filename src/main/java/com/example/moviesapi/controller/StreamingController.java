package com.example.moviesapi.controller;

import com.example.moviesapi.model.entity.Streaming;
import com.example.moviesapi.service.StreamingService;

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
@RequestMapping("streaming")
public class StreamingController {

  @Autowired
  StreamingService streamingService;

  @GetMapping("/list")
  public List<Streaming> getAllStreamings() {
    return streamingService.getAllStreamings();
  }

  @GetMapping("/{id}")
  public Streaming getStreamingById(@PathVariable Long id) {
    return streamingService.getStreamingById(id);
  }

  @PostMapping("/save")
  public Streaming registerStreaming(@RequestBody Streaming streaming) {
    return streamingService.registerStreaming(streaming);      
  }

  @PutMapping("/update")
  public Streaming updateStreaming(@RequestBody Streaming streaming) {
      return streamingService.updateStreaming(streaming);
  }

  @DeleteMapping("/delete/{id}")
  public String deleteStreaming(@PathVariable Long id){
    return streamingService.deleteStreaming(id);
  }
}
