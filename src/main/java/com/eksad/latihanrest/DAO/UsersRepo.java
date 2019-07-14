package com.eksad.latihanrest.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eksad.latihanrest.model.Users;

public interface UsersRepo extends JpaRepository<Users, Integer> {

	public Users  findByUsername(String username);
}
