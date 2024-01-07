package com.khan.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.khan.springcloud.dto.Coupon;
import com.khan.springcloud.model.Product;
import com.khan.springcloud.repo.ProductRepo;

@RestController
@RequestMapping("/productapi")
public class ProductRestController {

	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${couponService.url}")
	private String couponServiceUrl;
	
	@PostMapping("/product")
	public Product create(@RequestBody Product product) {
		Coupon coupon = restTemplate
				.getForObject(couponServiceUrl+product.getCouponCode(),
				Coupon.class);
		product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
		return productRepo.save(product);
		
	}
	public Product sendErrorResponse(Product product) {
		return product;
	}
}
