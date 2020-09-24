package com.assessment.main.model;

import java.util.List;

public class Purchase {

	
	private int userId;
	private List<Item> itemsLists;
	
	
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public List<Item> getItemsLists() {
		return itemsLists;
	}
	public void setItemsLists(List<Item> itemsLists) {
		this.itemsLists = itemsLists;
	}
	@Override
	public String toString() {
		return "Purchase [userId=" + userId + ", itemsLists=" + itemsLists + "]";
	}
	
	
}
