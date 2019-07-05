package com.eksad.latihanrest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eksad.latihanrest.DAO.BrandDAO;
import com.eksad.latihanrest.DAO.ProductDAO;
import com.eksad.latihanrest.model.Brand;
import com.eksad.latihanrest.model.Product;
@RestController
@RequestMapping("product")
public class ProductController {

	@Autowired
	ProductDAO productDAO;
	
	@Autowired
	BrandDAO brandDAO;
	
	@RequestMapping("getByBrandId/{brandId}")
	public List<Product> getByBrandId(@PathVariable Long brandId) {
		List<Product> result = new ArrayList<Product>();
		productDAO.findByBrandId(brandId).forEach(result::add);
		
		return result;
		
	}
	
	@RequestMapping(value ="save", method = RequestMethod.POST)
	public Product save(@RequestBody Product product) {
		Brand brand = brandDAO.findById(product.getBrand_id()).orElse(null);
		if (brand !=null) {
			product.setBrand(brand);
			return productDAO.save(product);
		}
		return null;
		
	}
}
