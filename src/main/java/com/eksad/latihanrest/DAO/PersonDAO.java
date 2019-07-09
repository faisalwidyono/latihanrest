package com.eksad.latihanrest.DAO;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.eksad.latihanrest.model.Person;

public interface PersonDAO extends PagingAndSortingRepository<Person, Long>{

}
