package com.assessment.main.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.main.model.OutputResponse;
import com.assessment.main.model.Purchase;
import com.assessment.main.serviceimpl.UserServiceImpl;

@RestController
@RequestMapping("/shop")
public class ShopController {
   
	
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	


	public UserServiceImpl getUserServiceImpl() {
		return userServiceImpl;
	}




	public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}




	@PostMapping(value="/items")
	public ResponseEntity<OutputResponse> billingItems(@RequestBody Purchase purchase) {
		
		OutputResponse outputResponse=userServiceImpl.calculation(purchase);
		if(outputResponse!=null) {
			return new ResponseEntity<OutputResponse>(outputResponse, HttpStatus.OK);
		}
		
		return ResponseEntity.badRequest().body(outputResponse);	
	}
	
	
	
}
