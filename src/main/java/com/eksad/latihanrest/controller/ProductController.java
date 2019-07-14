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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eksad.latihanrest.DAO.BrandDAO;
import com.eksad.latihanrest.DAO.ProductDAO;
import com.eksad.latihanrest.model.Brand;
import com.eksad.latihanrest.model.Product;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@RestController
@RequestMapping(value = "admin/api/v1")
@Api(tags = "Product")
public class ProductController {

	@Autowired
	ProductDAO productDAO;
	
	@Autowired
	BrandDAO brandDAO;
	@ApiOperation(
			value = "API to retrieve Product's data by Brand Id",
		    notes = "Return data with JSON Format",
		    tags = "Get Data API"
			)
	@GetMapping("getByBrandId/{brandId}")
	public List<Product> getByBrandId(@PathVariable Long brandId) {
		List<Product> result = new ArrayList<Product>();
		productDAO.findByBrandId(brandId).forEach(result::add);
		
		return result;
		
	}
	@ApiOperation(
			value = "Saving Product's data",
		    notes = "Saving Product's data to database",
		    tags = "Data Manipulation API"
			)
	@PostMapping("saveProduct")
	public Product save(@RequestBody Product product) {
		Brand brand = brandDAO.findById(product.getBrand_id()).orElse(null);
		if (brand !=null) {
			product.setBrand(brand);
			return productDAO.save(product);
		}
		return null;
		
	}
	@ApiOperation(
			value = "Update Product's data",
		    notes = "Update Product's data to database",
		    tags = "Data Manipulation API"
			)
	@PutMapping("updateProduct/{id}")
	public Product update(@RequestBody Product product, @PathVariable Long id) {
		
		Product productSelected = productDAO.findById(id).orElse(null);
		
		if (productSelected !=null) {
			productSelected.setName(product.getName());
			productSelected.setBrand_id(product.getBrand_id());
			productSelected.setPrice(product.getPrice());
			productSelected.setBrand(product.getBrand());
			
			
			return productDAO.save(productSelected);
		}else {
			return null;
		}
	}
	@ApiOperation(
			value = "Delete Product's data",
		    notes = "Delete Product's data to database",
		    tags = "Data Manipulation API"
			)
	@DeleteMapping("deleteProduct/{id}")
	public HashMap<String, Object> delete (@PathVariable Long id){
		HashMap<String, Object> result = new HashMap<String, Object>();
		productDAO.deleteById(id);
		result.put("message", "Berhasil Dihapus");
		return result;
		
	}
	@ApiOperation(
			value = "API to retrieve Product's data by Name",
		    notes = "Return data with JSON Format",
		    tags = "Get Data API"
			)
	@GetMapping("getByName/{name}")
	public List<Product> getByName(@PathVariable String name) {
		List<Product> result = new ArrayList<Product>();
		productDAO.findByName(name).forEach(result::add);
		
		return result;
		
	}

}
