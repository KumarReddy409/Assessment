package com.assessment.main.model;

public class OutputResponse {
	
	private double totalAmount;
	private double totalAmountWithOutGroceries;
	private double totalDiscount;
	private double netAmount;

	
	
	
	
	public OutputResponse() {
		super();
	}

	public OutputResponse(double totalAmount, double totalAmountWithOutGroceries, double totalDiscount,
			double netAmount) {
		super();
		this.totalAmount = totalAmount;
		this.totalAmountWithOutGroceries = totalAmountWithOutGroceries;
		this.totalDiscount = totalDiscount;
		this.netAmount = netAmount;
	}
	
	public double getTotalAmountWithOutGroceries() {
		return totalAmountWithOutGroceries;
	}
	public void setTotalAmountWithOutGroceries(double totalAmountWithOutGroceries) {
		this.totalAmountWithOutGroceries = totalAmountWithOutGroceries;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public double getTotalDiscount() {
		return totalDiscount;
	}
	public void setTotalDiscount(double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}
	public double getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}
	@Override
	public String toString() {
		return "OutputResponse [totalAmount=" + totalAmount + ", totalAmountWithOutGroceries="
				+ totalAmountWithOutGroceries + ", totalDiscount=" + totalDiscount + ", netAmount=" + netAmount + "]";
	}
	
	

}
