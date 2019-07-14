package com.eksad.latihanrest.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eksad.latihanrest.DAO.UsersRepo;
import com.eksad.latihanrest.model.Users;

@RestController
@RequestMapping("")
public class MainController {
	
	@Autowired
	UsersRepo usersRepo;
	
	@RequestMapping("admin")
	public HashMap<String, Object> admin() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("message", "masuk sebagai admin");
		return map;
	}
	
	@RequestMapping("user")
	public HashMap<String, Object> user() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("message", "masuk sebagai user");
		return map;
	}
	
	@GetMapping("admin/getAllUser")
	public List<Users> getAllUser() {
		return usersRepo.findAll();
	}
	
	@GetMapping("user/getAllUser")
	public List<Users> getAllUser1() {
		return usersRepo.findAll();
	}
	
	@PostMapping("admin/saveUser")
	public Users save(@RequestBody Users user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		return usersRepo.save(user);
	}
	
	@DeleteMapping("admin/delete/{id}")
	public String delete(@PathVariable int id) {
		usersRepo.deleteById(id);
		return "Data ID= " +(id)+" Berhasil Dihapus";
	}
	
	
	
}
