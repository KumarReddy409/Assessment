package com.assessment.main.service;

import com.assessment.main.entity.User;
import com.assessment.main.model.OutputResponse;
import com.assessment.main.model.Purchase;

public interface UserService {

	
	OutputResponse calculation(Purchase purchase);
}
