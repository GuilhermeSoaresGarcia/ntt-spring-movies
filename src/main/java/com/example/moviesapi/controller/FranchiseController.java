package com.example.moviesapi.controller;

import com.example.moviesapi.model.entity.Franchise;
import com.example.moviesapi.service.FranchiseService;

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
@RequestMapping("franchise")
public class FranchiseController {

  @Autowired
  FranchiseService franchiseService;

  @GetMapping("/list")
  public List<Franchise> getAllFranchises() {
    return franchiseService.getAllFranchises();
  }

  @GetMapping("/{id}")
  public Franchise getFranchiseById(@PathVariable Long id) {
    return franchiseService.getFranchiseById(id);
  }

  @PostMapping("/save")
  public Franchise registerFranchise(@RequestBody Franchise franchise) {
    return franchiseService.registerFranchise(franchise);
  }

  @PutMapping("/update")
  public Franchise updateFranchise(@RequestBody Franchise franchise) {
    return franchiseService.updateFranchise(franchise);
  }

  @DeleteMapping("/delete/{id}")
  public String deleteFranchise(@PathVariable Long id) {
    return franchiseService.deleteFranchise(id);
  }
}
