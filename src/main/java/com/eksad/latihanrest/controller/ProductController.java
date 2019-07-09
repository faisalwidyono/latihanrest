package com.eksad.latihanrest.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
	
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
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
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	public HashMap<String, Object> delete (@PathVariable Long id){
		HashMap<String, Object> result = new HashMap<String, Object>();
		productDAO.deleteById(id);
		result.put("message", "Berhasil Dihapus");
		return result;
		
	}
	
	@RequestMapping(value = "getByName/{name}", method = RequestMethod.GET)
	public List<Product> getByName(@PathVariable String name) {
		List<Product> result = new ArrayList<Product>();
		productDAO.findByName(name).forEach(result::add);
		
		return result;
		
	}

}
