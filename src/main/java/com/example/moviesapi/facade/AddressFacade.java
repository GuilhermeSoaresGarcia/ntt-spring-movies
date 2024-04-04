package com.example.moviesapi.facade;

import java.util.Map;

import com.example.moviesapi.dto.AddressDTO;

public interface AddressFacade {
  AddressDTO saveAddress(Map<String, Long> requestbody);
}
