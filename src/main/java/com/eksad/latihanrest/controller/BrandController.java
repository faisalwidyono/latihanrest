package com.eksad.latihanrest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eksad.latihanrest.DAO.BrandDAO;
import com.eksad.latihanrest.DAO.UsersRepo;
import com.eksad.latihanrest.model.Brand;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "admin/api/v1/")
@Api(tags = "Brand")
public class BrandController {
	@Autowired
	UsersRepo usersRepo;
	@Autowired
	BrandDAO brandDAO;
	@ApiOperation(
			value = "API to retrieve all Brand's data",
		    notes = "Return data with JSON Format",
		    tags = "Get Data API"
			)
	@GetMapping("getAllBrand")
	public List<Brand> getAllBrand() {
		List<Brand> result = new ArrayList<>();
		
		brandDAO.findAll().forEach(result::add);
	
		return result;
		
	}
	@ApiOperation(
			value = "API to retrieve all Brand's data",
		    notes = "Return data with JSON Format",
		    tags = "Get Data API"
			)
	@GetMapping("getOneBrand/{id}")
	public Brand getOneBrand(@PathVariable Long id) {
		return brandDAO.findById(id).orElse(null);
	}
	@ApiOperation(
			value = "Saving new Division's data",
		    notes = "Saving new Division's data to database",
		    tags = "Data Manipulation API"
			)
	@PostMapping("saveBrand")
	public Brand saveBrand(@RequestBody Brand brand) {
		try {
			
			
			return brandDAO.save(brand);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
				
	}
	@ApiOperation(
			value = "Update Division's data",
		    notes = "Update Division's data to database",
		    tags = "Data Manipulation API"
			)
	@PutMapping("updateBrand/{id}")
	public Brand updateBrand(@RequestBody Brand brand, @PathVariable Long id) {
		
		Brand brandSelected = brandDAO.findById(id).orElse(null);
		
		if (brandSelected !=null) {
			brandSelected.setName(brand.getName());
			brandSelected.setProductType(brand.getProductType());
			
			
			return brandDAO.save(brandSelected);
		}else {
			return null;
		}
	}
	@ApiOperation(
			value = "Delete Division's data",
		    notes = "Delete Division's data to database",
		    tags = "Data Manipulation API"
			)
	@DeleteMapping("deleteBrand/{id}")
	public HashMap<String, Object> deleteBrand (@PathVariable Long id){
		HashMap<String, Object> result = new HashMap<String, Object>();
		brandDAO.deleteById(id);
		result.put("message", "Berhasil Dihapus");
		return result;
		
	}
}          
