package com.assessment.main;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.assessment.main.controller.ShopController;
import com.assessment.main.entity.Discount;
import com.assessment.main.entity.User;
import com.assessment.main.model.Item;
import com.assessment.main.model.OutputResponse;
import com.assessment.main.model.Purchase;
import com.assessment.main.repository.DiscountRepository;
import com.assessment.main.repository.UserRepository;
import com.assessment.main.serviceimpl.UserServiceImpl;

@SpringBootTest
public class ControllerTestCase {
	
	
	@InjectMocks
	private  UserServiceImpl userServiceImpl;
	
	@InjectMocks
	private ShopController shopController;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private DiscountRepository discountRepository;
	
	@BeforeEach
	public  void setUp() {
		userServiceImpl.setDiscountRepository(discountRepository);
		userServiceImpl.setUserRepository(userRepository);
		shopController.setUserServiceImpl(userServiceImpl);
		
	}
	
	
	 @Test
	    public void testBillingItemsForEmployee() 
	    {
	        MockHttpServletRequest request = new MockHttpServletRequest();
	        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	        Optional<User> optionalUser=Optional.of(testUserEmployeeData());
	        when(userServiceImpl.getUserRepository().findById(testPurchaseDataForEmployee().getUserId())).thenReturn(optionalUser);
	        when(userServiceImpl.getDiscountRepository().discountByUserType(testUserEmployeeData().getEmployeeType())).thenReturn(mapDiscount().get("employee"));
	        ResponseEntity<OutputResponse> responseEntity = shopController.billingItems(testPurchaseDataForEmployee());
	         
	        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	        assertEquals(23620,responseEntity.getBody().getTotalAmount());
	        assertEquals(7080,responseEntity.getBody().getTotalDiscount());
	    }
	 @Test
	    public void testBillingItemsForAffiliate() 
	    {
	        MockHttpServletRequest request = new MockHttpServletRequest();
	        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	        Optional<User> optionalUser=Optional.of(testUserAffiliateData());
	        when(userServiceImpl.getUserRepository().findById(testPurchaseDataForAffiliate().getUserId())).thenReturn(optionalUser);
	        when(userServiceImpl.getDiscountRepository().discountByUserType(testUserAffiliateData().getEmployeeType())).thenReturn(mapDiscount().get("affiliate"));
	        ResponseEntity<OutputResponse> responseEntity = shopController.billingItems(testPurchaseDataForAffiliate());
	         
	        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	        assertEquals(23620,responseEntity.getBody().getTotalAmount());
	        assertEquals(2360,responseEntity.getBody().getTotalDiscount());
	       
	    }
	@Test
	public void testBillingItemsOldCustomer()
	{
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		Optional<User> optionalUser=Optional.of(testUserCustomerOverTwoYearsData());
		when(userServiceImpl.getUserRepository().findById(testPurchaseDataForCustomerOversTwoYears().getUserId())).thenReturn(optionalUser);
		when(userServiceImpl.getDiscountRepository().discountByUserType(testUserNewCustomerData().getEmployeeType())).thenReturn(mapDiscount().get("oldCustomer"));
		ResponseEntity<OutputResponse> responseEntity = shopController.billingItems(testPurchaseDataForCustomerOversTwoYears());

		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
		assertEquals(23620,responseEntity.getBody().getTotalAmount());
		assertEquals(1180,responseEntity.getBody().getTotalDiscount());
	}
	@Test
	public void testBillingItemsNewCustomer()
	{
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		Optional<User> optionalUser=Optional.of(testUserNewCustomerData());
		when(userServiceImpl.getUserRepository().findById(testPurchaseDataForCustomerFromLessthanTwoYears().getUserId())).thenReturn(optionalUser);
		when(userServiceImpl.getDiscountRepository().discountByUserType(testUserNewCustomerData().getEmployeeType())).thenReturn(mapDiscount().get("newCustomer"));
		ResponseEntity<OutputResponse> responseEntity = shopController.billingItems(testPurchaseDataForCustomerFromLessthanTwoYears());

		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
		assertEquals(23620,responseEntity.getBody().getTotalAmount());
		assertEquals(1180,responseEntity.getBody().getTotalDiscount());
	}
	 
	 public Map<String,Discount> mapDiscount(){
		 Map<String, Discount> discountsMap=new HashMap<>();
		   discountsMap.put("employee", new Discount(1,"employee",30));
		   discountsMap.put("affiliate", new Discount(1,"affiliate",10));
		   discountsMap.put("oldCustomer", new Discount(1,"customer",5));
		   discountsMap.put("newCustomer", new Discount(1,"customer",5));
		   return discountsMap;
	 }

	 public User testUserEmployeeData() {
		 User user=new User();
		 user.setName("Arjun");
		 user.setPassword("123");
		 user.setUserId(1);
		 user.setYears(2);
		 user.setEmployeeType("employee");
		 return user;
	 }
	 public User testUserAffiliateData() {
		 User user=new User();
		 user.setName("Arjun");
		 user.setPassword("123");
		 user.setUserId(2);
		 user.setYears(2);
		 user.setEmployeeType("affiliate");
		 return user;
	 }
	 public User testUserCustomerOverTwoYearsData() {
		 User user=new User();
		 user.setName("Arjun");
		 user.setPassword("123");
		 user.setUserId(3);
		 user.setYears(2);
		 user.setEmployeeType("oldCustomer");
		 return user;
	 }
	 public User testUserNewCustomerData() {
		 User user=new User();
		 user.setName("Arjun");
		 user.setPassword("123");
		 user.setUserId(4);
		 user.setYears(1);
		 user.setEmployeeType("newCustomer");
		 return user;
	 }
	 public Purchase testPurchaseDataForEmployee() {
		 Purchase purchase=new Purchase();
		 purchase.setUserId(1);
		 purchase.setItemsLists(testItemData());
		 return purchase;
	 }
	 public Purchase testPurchaseDataForAffiliate() {
		 Purchase purchase=new Purchase();
		 purchase.setUserId(2);
		 purchase.setItemsLists(testItemData());
		 return purchase;
	 }
	 public Purchase testPurchaseDataForCustomerOversTwoYears() {
		 Purchase purchase=new Purchase();
		 purchase.setUserId(3);
		 purchase.setItemsLists(testItemData());
		 return purchase;
	 }
	 public Purchase testPurchaseDataForCustomerFromLessthanTwoYears() {
		 Purchase purchase=new Purchase();
		 purchase.setUserId(4);
		 purchase.setItemsLists(testItemData());
		 return purchase;
	 }
	 public List<Item> testItemData(){
		 List<Item> items=new  ArrayList<Item>();
		 items.add(new Item("bananana", "groceries",20));
		 items.add(new Item("laptop", "electronic",20000));
		 items.add(new Item("mobile", "electronic",3000));
		 items.add(new Item("t-shirt", "cloths",600));
		return items;
		
		
	 }
}
