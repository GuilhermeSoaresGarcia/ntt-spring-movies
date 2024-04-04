package com.example.moviesapi.dto;

import com.example.moviesapi.model.entity.Address;
import com.example.moviesapi.model.entity.Movie;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String username;
    private String email;
    private List<Movie> favorites;
    private Address address;
}