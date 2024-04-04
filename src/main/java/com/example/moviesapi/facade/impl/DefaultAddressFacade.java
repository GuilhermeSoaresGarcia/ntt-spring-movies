package com.example.moviesapi.facade.impl;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.moviesapi.dto.AddressDTO;
import com.example.moviesapi.dto.UserDTO;
import com.example.moviesapi.facade.AddressFacade;
import com.example.moviesapi.facade.UserFacade;
import com.example.moviesapi.model.entity.Address;
import com.example.moviesapi.model.entity.User;
import com.example.moviesapi.service.AddressService;
import com.example.moviesapi.service.UserService;

@Service
public class DefaultAddressFacade implements AddressFacade {

  @Autowired
  AddressService addressService;
  
  @Autowired
  UserFacade userFacade;

  @Autowired
  UserService userService;

  @Override
  public AddressDTO saveAddress(Map<String, Long> requestbody) {    
    Address savedAddress = addressService.getAddressWithCep(requestbody.get("cep"));
    UserDTO userDto = userFacade.getUserById(requestbody.get("id"));
    User user = userService.getUserById(userDto.getId()).get();
    user.setAddress(savedAddress);
    userService.saveUser(user);

    ModelMapper modelMapper = new ModelMapper();

    AddressDTO result = modelMapper.map(savedAddress, AddressDTO.class);

    return result;
  };
}
