package com.company.repository;

import com.company.domain.basicsOfBasics.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}