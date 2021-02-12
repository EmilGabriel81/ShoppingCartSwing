package com.shopgui.model;


import java.util.ArrayList;
import java.util.List;
import com.shopgui.objects.*;


public class Database {


	private ArrayList<Item> items;
	private ArrayList<Item> itemsInventory;
	
	/*
	 * Cart Content
	 */

	List<ShoppingCartEntry> cartItems;
	
	public Database() {
		items = new ArrayList<Item>();
		itemsInventory = new ArrayList<Item>();
		
		cartItems = new ArrayList<ShoppingCartEntry>();
		
	}

	public void addItem(Item item) {
		items.add(item);
		//Item item2 = new Item("Hook", "Mustad", 3.5, 0.75, 20, "Universal");
		//items.add(item2);
		System.out.println("There are "+items.size()+" elements in the BackEnd");
	}

	public List<Item> getItems() {
		return items;
	}
	
	/*
	 * Changes
	 */
	
	
	public void addInventory(Item item) {
		
		itemsInventory.add(item);
//		for(Item itm : itemsInventory) {
//			System.out.println(itm.toString());
//		}
		System.out.println("Total Items in inventory : "+itemsInventory.size());
	}

	public List<Item> getItemsInventory() {
		return itemsInventory;
	}
	
	/**
	 * Cart Content db
	 * 
	 */
	
	public void addEntries(ShoppingCartEntry entry) {
		cartItems.add(entry);
		System.out.println("Items in the Cart : " + items.size());
	}

	public List<ShoppingCartEntry> getCartItems() {
		return cartItems;
	}
}
