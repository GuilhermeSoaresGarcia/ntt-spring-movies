package com.example.moviesapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.moviesapi.model.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
