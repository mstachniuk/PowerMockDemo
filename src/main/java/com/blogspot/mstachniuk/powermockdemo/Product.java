package com.blogspot.mstachniuk.powermockdemo;

public class Product {

	private final String name;
	private final double netPrice;
	
	public Product(String name, double netPrice) {
		this.name = name;
		this.netPrice = netPrice;
	}

	public String getName() {
		return name;
	}

	public double getNetPrice() {
		return netPrice;
	}
	
	
}
