package com.example.moviesapi.service;

import com.example.moviesapi.dto.UserDTO;
import com.example.moviesapi.model.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMapperService {
  private static ModelMapper modelMapper = new ModelMapper();
  
  public UserMapperService(ModelMapper modelMapper) {
    UserMapperService.modelMapper = modelMapper;
  }

  public static UserDTO mapUserToUserDTO(User user) {
    return modelMapper.map(user, UserDTO.class);
  }

  public static List<UserDTO> mapUsersToUserDTOs(List<User> users) {
    return users.stream().map(user -> mapUserToUserDTO(user)).toList();
  }
}
