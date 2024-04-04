package com.example.moviesapi.service;

import com.example.moviesapi.model.entity.Address;
import com.example.moviesapi.model.repository.AddressRepository;

import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AddressService {

  @Autowired
  AddressRepository addressRepository;

  public Address getAddressWithCep(Long cep) {
    Pattern pattern = Pattern.compile("^\\d{8}$");
    Boolean matcher = pattern.matcher(cep.toString()).matches();
    
    String cepFormated = cep
    .toString().substring(0, 5)
    + "-"
    + cep.toString().substring(5);
    Address verifyCep = addressRepository.findFirstByCep(cepFormated);    
    
    if (matcher && verifyCep != null) {
      throw new RuntimeException("CEP já cadastrado");
    }
    
    String URI = "https://viacep.com.br/ws/" + cep + "/json/";
    RestTemplate restTemplate = new RestTemplate();

    try {
      ResponseEntity<Address> responseEntity = restTemplate
          .getForEntity(URI, Address.class);
      @SuppressWarnings("null")
      Address result = addressRepository.save(responseEntity.getBody());
      return result;
    } catch (Exception e) {
      throw new RuntimeException(
          "Não foi possível localizar um endereço com o CEP informado");
    }
  }
}
