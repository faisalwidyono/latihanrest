package com.eksad.latihanrest.DAO;

import org.springframework.data.repository.CrudRepository;

import com.eksad.latihanrest.model.Customer;

public interface CustomerDAO extends CrudRepository<Customer, Long>{

}
