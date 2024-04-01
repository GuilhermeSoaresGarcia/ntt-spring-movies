package com.example.moviesapi.service;

import com.example.moviesapi.dto.UserDTO;
import com.example.moviesapi.model.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMapperService {
  private final ModelMapper modelMapper;
  
  public UserMapperService(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public UserDTO mapUserToUserDTO(User user) {
    return modelMapper.map(user, UserDTO.class);
  }

  public List<UserDTO> mapUsersToUserDTOs(List<User> users) {
    return users.stream().map(user -> mapUserToUserDTO(user)).toList();
  }
}
