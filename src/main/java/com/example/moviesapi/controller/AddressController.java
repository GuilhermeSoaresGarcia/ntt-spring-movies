package com.example.moviesapi.controller;

import com.example.moviesapi.facade.AddressFacade;
import com.example.moviesapi.util.ResponseUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("address")
public class AddressController {

  @Autowired
  AddressFacade addressFacade;

  @PostMapping("/save")
  public ResponseEntity<?> saveAddressInfo(@RequestBody Map<String, Long> requestBody) {
    try {
      return ResponseEntity.ok(ResponseUtil.getResult(
          "Endereço do usuário salvo com sucesso",
          200,
          addressFacade.saveAddress(requestBody)));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseUtil.getResult(
          e.getMessage(),
          400,
          null));
    }
  }
}
