package com.example.moviesapi.controller;

import com.example.moviesapi.model.entity.Studio;
import com.example.moviesapi.service.StudioService;

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
@RequestMapping("studio")
public class StudioController {

  @Autowired
  StudioService studioService;

  @GetMapping("/list")
  public List<Studio> getAllStudios() {
    return studioService.getAllStudios();
  }

  @GetMapping("/{id}")
  public Studio getStudioById(@PathVariable Long id) {
    return studioService.getStudioById(id);
  }

  @PostMapping("/save")
  public Studio registerStudio(@RequestBody Studio studio) {
    return studioService.registerStudio(studio);      
  }

  @PutMapping("/update")
  public Studio updateStudio(@RequestBody Studio studio) {
      return studioService.updateStudio(studio);
  }

  @DeleteMapping("/delete/{id}")
  public String deleteStudio(@PathVariable Long id){
    return studioService.deleteStudio(id);
  }
}
