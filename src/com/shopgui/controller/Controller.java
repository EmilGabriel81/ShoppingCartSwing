package com.shopgui.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import javax.swing.JOptionPane;

import com.shopgui.objects.*;
import com.shopgui.model.*;

public class Controller {
	
	private String infoMessage;
	Database db = new Database();

	public List<Item> getItems() {
		return db.getItems();
	}

	public List<Item> getInvItems() {
		return db.getItemsInventory();
	}

	/*
	 * Changes
	 */

	public Item findTheItem(String name, double size) {

		Item searchedItem = null;
		List<Item> myItems = getInvItems();

		System.out.println("There are " + myItems.size() + " elements in find method (Controller)");

		for (Item itm : myItems) {
			if (itm.getItemName().equalsIgnoreCase(name) && itm.getItemSize() == size) {
				searchedItem = itm;
			}
		}
		System.out.println("searched item is " + searchedItem);
		return searchedItem;

	}

	public void loadFromFile(String fileName) {
		// String filePath = "src/" + fileName + ".txt";
		String line = "";
		db.getItemsInventory().clear();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null) {
				fetchData(line);
				// System.out.println(line);
			}

		} catch (Exception e) {
			// System.out.println(e.getMessage()+" here ");
			JOptionPane.showMessageDialog(null, "The system cannot find the file specified", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void fetchData(String line) {
		String[] tokens = line.split(",");
		int id = Integer.parseInt(tokens[0]);
		String name = tokens[1];
		String brand = tokens[2];
		double size = Double.parseDouble(tokens[3]);
		double price = Double.parseDouble(tokens[4]);
		int quantity = Integer.parseInt(tokens[5]);
		String description = tokens[6];
		boolean onSale = Boolean.parseBoolean(tokens[7]);
		boolean onStock = Boolean.parseBoolean(tokens[8]);

		// System.out.println(id+" "+name+" "+brand+" "+size+" "+price+ " "+quantity+"
		// "+description+" "+onSale+" "+onStock+"\n");
		Item item = new Item();
		item.setId(id);
		item.setItemName(name);
		item.setItemBrand(brand);
		item.setItemSize(size);
		item.setItemPrice(price);
		item.setItemQuantity(quantity);
		item.setItemDescription(description);
		item.setItemOnSale(onSale);
		item.setItemOnStock(onStock);
		db.addInventory(item);

	}

	public void init() {

		Item item01 = new Item("Hook", "Mustad", 3.5, 0.75, 20, "Universal");
		Item item02 = new Item("Hook", "Mustad", 2.5, 0.50, 20, "Universal");
		Item item03 = new Item("Sinker", "Daiwa", 5.0, 1.00, 20, "Universal");
		Item item04 = new Item("Fishing line", "Arrow", 0.25, 1.50, 20, "Universal");
		db.addItem(item01);
		db.addItem(item02);
		db.addItem(item03);
		db.addItem(item04);
	}

	/**
	 * Cart Content Controller
	 * 
	 */

	public void addToCart(ShoppingCartEntry entry) {
		db.addEntries(entry);
		System.out.println("Entry added, total items in cart:" + db.getCartItems().size());

	}

	public List<ShoppingCartEntry> getCartContent() {
		return db.getCartItems();
	}

	public double getTotalPrice() {

		double total = 0;

		for (ShoppingCartEntry entry : getCartContent()) {
			total += entry.getTotalPrice();
		}
		return total;
	}

	public String getCartDetails() {
		StringBuilder cartItems = new StringBuilder();
		cartItems.append("-------------------------------------------------------\n");

		for (ShoppingCartEntry en : getCartContent()) {
			// cartItems=en.getName()+" "+en.getPrice()+" "+en.getQuantity()+"\n";
			cartItems.append(en.getName() + ", " + en.getProdDescr() + ", " + en.getProdSize() + ", " + en.getPrice()
					+ ", " + en.getQuantity() + " -> Total : " + en.getTotalPrice() + "\n");
			System.out.println(en.getName() + " " + en.getPrice() + " " + en.getQuantity() + "\n");
		}
		cartItems.append("-------------------------------------------------------\n");
		String build = cartItems.toString();
		return build;
	}

	public int cancelOrder() {
		if (!this.getCartContent().isEmpty()) {
			getCartContent().clear();
			return 0;
		}
		return -1;
	}

	public int submitOrder() {
		infoMessage ="";
		if (!getCartContent().isEmpty()) {
			for(Item itm : getInvItems()) {
				for(ShoppingCartEntry entry : getCartContent()) {
					if(itm.getItemName().equalsIgnoreCase(entry.getName()) && itm.getItemSize()==entry.getProdSize()){
						System.out.println("Item : "+itm.getItemName()+" ->"+itm.getItemQuantity()+
								          " Entry : "+entry.getName()+" "+entry.getQuantity());
						itm.setItemQuantity(itm.getItemQuantity()-entry.getQuantity());
						System.out.println("Items left in store : "+itm.getItemName()+" "+itm.getItemQuantity());
						setInfoMessage(infoMessage.concat(entry.getName()+" "+entry.getProdDescr()+
								                          " "+entry.getQuantity()+" pcs"+"  -----------------    Total "+
								                              entry.getTotalPrice()+" $ \n"));
						
						
					}
				}
			}
			setInfoMessage(getInfoMessage()+" \n"+"Total price : "+getTotalPrice()+" $");
			getCartContent().clear();
			return 0;
		}
		return -1;
	}

	public String getInfoMessage() {
		return infoMessage;
	}

	public void setInfoMessage(String infoMessage) {
		this.infoMessage = infoMessage;
	}
	
	
}
