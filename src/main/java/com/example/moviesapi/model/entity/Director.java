package com.example.moviesapi.model.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "directors")
@Data
@NoArgsConstructor
public class Director {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @NotBlank
  private String name;

  @ManyToMany
  @JoinTable(
    name = "director_movie", 
    joinColumns = @JoinColumn(name = "director_id"), 
    inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
  private List<Movie> movies;

}
