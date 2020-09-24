package com.assessment.main.model;

public class Item {

	
	private String item;
	private String itemType;
	private double cost;
	
	
	
	public Item(String item, String itemType, double cost) {
		super();
		this.item = item;
		this.itemType = itemType;
		this.cost = cost;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	@Override
	public String toString() {
		return "ItemsList [item=" + item + ", itemType=" + itemType + ", cost=" + cost + "]";
	}
	
	
}
