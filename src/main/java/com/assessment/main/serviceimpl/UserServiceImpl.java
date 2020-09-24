package com.assessment.main.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.main.entity.Discount;
import com.assessment.main.entity.User;
import com.assessment.main.model.Item;
import com.assessment.main.model.OutputResponse;
import com.assessment.main.model.Purchase;
import com.assessment.main.repository.DiscountRepository;
import com.assessment.main.repository.UserRepository;
import com.assessment.main.service.UserService;





/**
 * @author KUMAR
 *
 */
@Service
public class UserServiceImpl implements UserService {

	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DiscountRepository discountRepository;
	
	public UserRepository getUserRepository() {
		return userRepository;
	}
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	public DiscountRepository getDiscountRepository() {
		return discountRepository;
	}
	public void setDiscountRepository(DiscountRepository discountRepository) {
		this.discountRepository = discountRepository;
	}





	
	
	OutputResponse outputResponse=new OutputResponse();
	
	
	/**
	 * Calculation method for calculating net Amount
	 * Input-parameter:-Purchase Class
	 * Output:-Output Response
	 */
	@Override
	public OutputResponse calculation(Purchase purchase) {
		
		Optional<User> userOptional=userRepository.findById(purchase.getUserId());
		  User user= userOptional.get();
		if(user!=null) {
		
		   double totalAmountWithOutGroceries=calculateTotalAmount(purchase.getItemsLists());
		
		   outputResponse.setTotalAmountWithOutGroceries(totalAmountWithOutGroceries);
		   mapDiscount(totalAmountWithOutGroceries, user);
		   return outputResponse;
		  
		}
		else {
		outputResponse=null;
		}
		return outputResponse;
		
	}

	

	/**
	 * Calculation method for calculating Total Amount
	 * Input-parameter:-Items purchased
	 * Output:-Total amount
	 */
	private double calculateTotalAmount(List<Item> itemsLists) {
		 double totalAmount=0;
		double totalAmountWithOutGroceries=0;
		for(Item items:itemsLists) {
			if(!items.getItemType().equals("groceries")) {
				totalAmountWithOutGroceries=totalAmountWithOutGroceries+items.getCost();
			}
			totalAmount=totalAmount+items.getCost();
			outputResponse.setTotalAmount(totalAmount);
		}
		return totalAmountWithOutGroceries;
		
	}
	
	

	/**
	 * Calculation method for discount Amount
	 * Input-parameter 1:-total amount
	 * Input-parameter 2:-user
	 * Output:-Output Response
	 */
	private double mapDiscount(double totalAmount,User user) {
		double netAmount=0;
		if(user.getEmployeeType().equals("employee")) {
			Discount discount=discountRepository.discountByUserType(user.getEmployeeType());
			 netAmount=calculateNetAmount(totalAmount,discount.getDiscount());
			return netAmount;
		}
		else if(user.getEmployeeType().equals("affiliate")) {
			Discount discount=discountRepository.discountByUserType(user.getEmployeeType());
			 netAmount=calculateNetAmount(totalAmount,discount.getDiscount());
			return netAmount;
		}
		else {
		 netAmount=	customerDiscount(totalAmount,user.getYears());
		
		}
		return netAmount;
	}







	/**
	 * Calculation method for net Amount
	 * Input-parameter 1:-total amount
	 * Input-parameter 2:-discount
	 * Output:-net Amount
	 */
	
	private double calculateNetAmount(double totalAmount, int discount) {
	   double netAmount=0;
	   double discountAmount=(totalAmount*discount)/100;
	     netAmount=(totalAmount)-(discountAmount);
	     outputResponse.setNetAmount(netAmount+(outputResponse.getTotalAmount()-outputResponse.getTotalAmountWithOutGroceries()));
	     outputResponse.setTotalDiscount(discountAmount);
		return netAmount;
	}
	


   

	/**
	 * Calculation method for discount Amount
	 * Input-parameter 1:-total amount
	 * Input-parameter 2:-years
	 * Output:-Output Response
	 */
	private double customerDiscount(double totalAmount, int years) {
		double netAmount=0;
		   double discountAmount=0;
	   if(years>=2) {
		discountAmount=(totalAmount*5)/100;
		   netAmount=(totalAmount)-(discountAmount);
		   outputResponse.setTotalDiscount(discountAmount);
		   outputResponse.setNetAmount(netAmount+(outputResponse.getTotalAmount()-outputResponse.getTotalAmountWithOutGroceries()));
		   return netAmount;
	   }
	   else {
		  int amount=(int)outputResponse.getTotalAmount()/100;
		  discountAmount=amount*5;
		   netAmount=outputResponse.getTotalAmount()-(discountAmount);
		   outputResponse.setNetAmount(netAmount);
		   outputResponse.setTotalDiscount(discountAmount);
		   
	   }
	   return netAmount;
	}
	
	

}
