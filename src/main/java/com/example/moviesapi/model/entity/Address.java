package com.example.moviesapi.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  private Long id;

  @NotNull
  @NotBlank
  private String cep;

  @NotNull
  @NotBlank
  private String logradouro;
  private String bairro;

  @NotNull
  @NotBlank
  private String localidade;

  @NotNull
  @NotBlank
  private String uf;

  @OneToOne(mappedBy = "address", 
            cascade = {
              CascadeType.DETACH,
              CascadeType.MERGE,
              CascadeType.PERSIST,
              CascadeType.REFRESH }
              )
  @JsonIgnore
  private User user;
}