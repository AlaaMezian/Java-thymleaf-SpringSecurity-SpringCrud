package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.Entity.Product;
import com.example.repository.ProductRepository;


@Controller
public class ProductController {

	@Autowired
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(path = "/products/add", method = RequestMethod.GET)
    public String createProduct(Model model) {
        model.addAttribute("product", new Product());
        return "editProduct";
    }

    @RequestMapping(path = "products", method = RequestMethod.POST)
    public String saveProduct(Product product) {
    	
        productRepository.save(product);
        return "redirect:/products";
    }

    @RequestMapping(path = "/products")
    public String getAllProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }

    @RequestMapping(path = "/products/edit/{id}", method = RequestMethod.GET)
    public String editProduct(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("product", productRepository.findById(id));
        return "editProduct";
    }

    @RequestMapping(path = "/products/delete/{id}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable(name = "id") int id) {
        productRepository.deleteById(id);
        return "redirect:/products";
    }
    
   
}