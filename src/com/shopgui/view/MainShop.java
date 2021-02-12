package com.shopgui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import com.shopgui.controller.*;

import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import com.shopgui.model.*;
import com.shopgui.objects.Item;
import com.shopgui.objects.ShoppingCartEntry;


import java.awt.GridBagConstraints;
import java.awt.Insets;

public class MainShop extends JFrame{
	
	private Controller controller;
	private OrderTablePanel orderTbPanel;
	private PlaceOrderPanel placeOrderPanel;
	private OrderTextPanel orderTextPanel;
	
	
	public MainShop() {
		super("Main Shop");
		controller = new Controller();
		//controller.init();// we load the objects
		controller.loadFromFile("src/Inv01.txt");
		
		orderTbPanel = new OrderTablePanel();
		orderTbPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		orderTextPanel = new OrderTextPanel();
		orderTextPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		BorderLayout borderLayout = (BorderLayout) orderTextPanel.getLayout();
		borderLayout.setHgap(10);
		orderTbPanel.setData(controller.getInvItems());
		getContentPane().add(orderTbPanel, BorderLayout.NORTH);
		placeOrderPanel = new PlaceOrderPanel();
//		placeOrderPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		orderTbPanel.add(placeOrderPanel, BorderLayout.WEST);
		
		setJMenuBar(createMenuBar());
		getContentPane().add(orderTextPanel, BorderLayout.CENTER);
		
		placeOrderPanel.setOrderListener(new OrderListener() {
			@Override
			public void orderEmitted(String name, double size, int quantity) {
				
				Item itm = controller.findTheItem(name, size);
				try {
					ShoppingCartEntry entry = new ShoppingCartEntry(itm.getItemName(),itm.getItemBrand(),itm.getItemSize(),itm.getItemPrice(), quantity);
					controller.addToCart(entry);
					String orderDetails = "Order Emitted -> "+entry.getName()+" "+
					                                         entry.getProdDescr()+", Size :  "+
					                                         entry.getProdSize()+", Quantity : "+
							                                 entry.getQuantity()+", Price : "+
					                                         entry.getPrice()+" /pcs. \n";
					orderTextPanel.appendText(orderDetails);
					
				}catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null,"Item not available in store.","Alert",JOptionPane.WARNING_MESSAGE);
				}			
//				String orderDetails = "Order Emitted : "+name+", Size :  " +size+", Quantity : "+quantity+"\n";
//				orderTextPanel.appendText(orderDetails);
			}
		});
		
		
		setSize(900, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private JMenuBar createMenuBar() {
		//MenuBar
		JMenuBar menuBar = new JMenuBar();
		//The Menu
		JMenu cart = new JMenu("Shopping Cart");
		JMenuItem viewCart = new JMenuItem("View Cart");
		JMenu items = new JMenu("Items");
		JMenu showMenu = new JMenu("Show");
		JMenu ordersMenu = new JMenu("Orders");
		JMenuItem submitOrder = new JMenuItem("Submit Order");
		JMenuItem cancelOrder = new JMenuItem("Cancel Order");
		
		cart.add(viewCart);
		ordersMenu.add(submitOrder);
		ordersMenu.add(cancelOrder);
		
		menuBar.add(cart);
		menuBar.add(ordersMenu);
		
		viewCart.addActionListener(e ->{
			boolean cartEmpty = controller.getCartContent().isEmpty();
			if(!cartEmpty) {
				orderTextPanel.appendText(controller.getCartDetails());
				orderTextPanel.appendText("Total Price per cart -> "+controller.getTotalPrice());
			}else if (cartEmpty) {
				JOptionPane.showMessageDialog(this, "No items in the cart","Order Info", JOptionPane.INFORMATION_MESSAGE);
			}
			
			
		});
		
		submitOrder.addActionListener(e ->{
			int input = JOptionPane.showConfirmDialog(null, "Proceed to checkout", "Select an Option...",JOptionPane.YES_NO_OPTION);
			if(input == 0) {
				if(controller.submitOrder()==0) {
					orderTextPanel.clearTextArea();
					
					orderTextPanel.appendText(controller.getInfoMessage());
					orderTbPanel.refresh();
				}
				else if(controller.submitOrder()==-1) {
					JOptionPane.showMessageDialog(null, "Nothing to submit!","Order Info", JOptionPane.INFORMATION_MESSAGE);
				}
			
			}
		});
		
		cancelOrder.addActionListener(e ->{
			
			int input = JOptionPane.showConfirmDialog(null, "Do you want to cancel?", "Select an Option...",
	                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
			if(input==0) {
				if(controller.cancelOrder()==0) {
					String canceled = "\nTranzaction canceled ...\n ------------------------------------------------------------------------------------------------------- \n";
					orderTextPanel.appendText(canceled);
					JOptionPane.showMessageDialog(null, "The order has been canceled!","Order Info", JOptionPane.INFORMATION_MESSAGE);
				}else if (controller.cancelOrder()==-1) {
					JOptionPane.showMessageDialog(null, "The Shopping Cart is already empty","Order Info", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
			
		});
		
		// The Borders
		Border innerBorder = BorderFactory.createTitledBorder("");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		menuBar.setBorder(BorderFactory.createCompoundBorder(outerBorder , innerBorder));

		return menuBar;
	}
	

}
