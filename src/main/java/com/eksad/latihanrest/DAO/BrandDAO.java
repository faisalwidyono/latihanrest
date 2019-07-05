package com.eksad.latihanrest.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eksad.latihanrest.model.Brand;

public interface BrandDAO extends JpaRepository<Brand, Long> {
	
	public Brand findOneByName(String name);
	public List<Brand> findByName (String name);
	public List<Brand> findByProductType (String type);
	
	@Query("select b from Brand b where name = :search")
	public List<Brand> findBySearch(@Param("search") String search);
	
}
