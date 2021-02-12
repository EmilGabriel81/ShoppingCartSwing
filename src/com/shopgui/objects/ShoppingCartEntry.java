package com.shopgui.objects;

public class ShoppingCartEntry {

	private double price;
	private int quantity;
	private String name;
	private double prodSize;
	private String prodDescr;
	
	public ShoppingCartEntry(String name,String prodDescr,double prodSize, double price, int quantity) {
		this.name = name;
		this.prodDescr = prodDescr;
		this.prodSize = prodSize;
		this.price = price;
		this.quantity = quantity;
		
	}
	
	/*
	 * 
	 * 
	 */
	
	public double getTotalPrice() {
		return (double) (price*quantity);
	}
	public double getPrice() {
		return price;
	}
	public int getQuantity() {
		return quantity;
	}
	public String getName() {
		return name;
	}

	public double getProdSize() {
		return prodSize;
	}

	public void setProdSize(double prodSize) {
		this.prodSize = prodSize;
	}

	public String getProdDescr() {
		return prodDescr;
	}

	@Override
	public String toString() {
		return "ShoppingCartEntry -> price=" + price + ", quantity=" + quantity + ", name=" + name + ", prodSize="
				+ prodSize + ", prodDescr=" + prodDescr + "]";
	}

	

	
	
	
}
