package com.assessment.main.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Discounts")
public class Discount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="Id")
	private int id;
	@Column(name="type")
    private String employeeType;
	@Column(name="discount")
	private int discount;
	
	
	
	public Discount() {
		super();
	}
	public Discount(int id, String employeeType, int discount) {
		super();
		this.id = id;
		this.employeeType = employeeType;
		this.discount = discount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}
	
	
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	@Override
	public String toString() {
		return "Discounts [id=" + id + ", employeeType=" + employeeType + ", discount=" + discount + ", getId()="
				+ getId() + ", getEmployeeType()=" + getEmployeeType() + ", getDiscount()=" + getDiscount()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
}
