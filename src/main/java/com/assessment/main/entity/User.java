package com.assessment.main.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;




/**
 * @author KUMAR
 *User Entity Class
 */
@Entity
@Table(name="User")
public class User {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="Id")
	private int userId;
	@Column(name="Name")
	private String name;
	@Column(name="password")
	private String password;
	@Column(name="EmployeeType")
	private String employeeType;
	@Column(name="years")
	private int years;
	
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getYears() {
		return years;
	}
	public void setYears(int years) {
		this.years = years;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	/**
	 * toString method
	 */
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", password=" + password + ", employeeType=" + employeeType
				+ ", years=" + years + "]";
	}
	
	
	
	
	
	
}
