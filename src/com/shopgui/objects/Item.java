package com.shopgui.objects;

public class Item {

	private static int count = 1;
	private int id;
	private String itemName;
	private String itemBrand;
	private double itemSize;
	private double itemPrice;
	private int itemQuantity;
	private String itemDescription;
	private boolean itemOnSale;
	private boolean itemOnStock;
	
	public Item() {
		
	}
	
	public Item(String itemName, String itemBrand, double itemSize, double itemPrice, int itemQuantity,
			String itemDescription) {
		
		this.itemName = itemName;
		this.itemBrand = itemBrand;
		this.itemSize = itemSize;
		this.itemPrice = itemPrice;
		this.itemQuantity = itemQuantity;
		this.itemDescription = itemDescription;
		this.itemOnSale = false;
		this.itemOnStock = true;
		this.id = count;
		count++;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemBrand() {
		return itemBrand;
	}

	public void setItemBrand(String itemBrand) {
		this.itemBrand = itemBrand;
	}

	public double getItemSize() {
		return itemSize;
	}

	public void setItemSize(double itemSize) {
		this.itemSize = itemSize;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public boolean isItemOnSale() {
		return itemOnSale;
	}

	public void setItemOnSale(boolean itemOnSale) {
		this.itemOnSale = itemOnSale;
	}

	public boolean isItemOnStock() {
		return itemOnStock;
	}

	public void setItemOnStock(boolean itemOnStock) {
		this.itemOnStock = itemOnStock;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", itemName=" + itemName + ", itemBrand=" + itemBrand + ", itemSize=" + itemSize
				+ ", itemPrice=" + itemPrice + ", itemQuantity=" + itemQuantity + ", itemDescription=" + itemDescription
				+ ", itemOnSale=" + itemOnSale + ", itemOnStock=" + itemOnStock + "]";
	}
	
}
