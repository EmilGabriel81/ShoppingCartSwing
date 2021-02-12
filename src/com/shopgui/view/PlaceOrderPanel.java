package com.shopgui.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.border.Border;

import com.shopgui.constants.Constants;

public class PlaceOrderPanel extends JPanel{

	//buttons
	private JButton addOrderBtn;
	private JButton clearBtn;
	//labels
	private JLabel itemNameLabel;
	private JLabel itemSizeLabel;
	private JLabel itemQuantityLabel;
	//fields
	private JTextField itemNameField;
	private JTextField itemSizeField;
	private JTextField itemQuantityField;
	//other
	private OrderListener orderListener;
	
	public PlaceOrderPanel() {
		itemNameLabel = new JLabel("Name: ");
		itemNameLabel.setFont(Constants.mvBoli2);
		
		itemSizeLabel = new JLabel("Size: ");
		itemSizeLabel.setFont(Constants.mvBoli2);
		
		itemQuantityLabel = new JLabel("Quantity : ");
		itemQuantityLabel.setFont(Constants.mvBoli2);
		
		itemNameField = new JTextField(10);
		itemSizeField = new JTextField(10);
		itemQuantityField = new JTextField(10);
		
		addOrderBtn = new JButton("Add");
		addOrderBtn.setFont(Constants.mvBoli2);
		addOrderBtn.setFocusable(false);
		addOrderBtn.addActionListener(e ->{
			try {
			String orderName = itemNameField.getText();
			double orderSize = Double.parseDouble(itemSizeField.getText());
			int orderQuantity = Integer.parseInt(itemQuantityField.getText());
			
			if(orderListener!=null) {
				orderListener.orderEmitted(orderName, orderSize, orderQuantity);
			}
			}catch (Exception ex) {
				JOptionPane.showMessageDialog(null,"Input Mismatch, please check again ","Alert",JOptionPane.WARNING_MESSAGE);
				System.err.println(ex.getMessage());
			}
			
		});
		
		clearBtn = new JButton("Clear");
		clearBtn.setFont(Constants.mvBoli2);
		clearBtn.setFocusable(false);
		
		clearBtn.addActionListener(e ->{
			itemNameField.setText("");
			itemSizeField.setText("");
			itemQuantityField.setText("");
		});
		
		/**
		 *  The Layout
		 */
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		////// First Row///// Name
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		// Name Label
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(itemNameLabel, gc);
		// Name Field
		gc.gridx = 1;
		gc.gridy = 0;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(itemNameField, gc);
		//////////// Next Row///////////////// Brand
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.1;
		// Brand Label
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.LINE_END;
		// add(occupationLabel , gc);
		add(itemSizeLabel, gc);
		// Brand Field
		gc.gridx = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(itemSizeField, gc);
		///////////// Next Row//////////////////// Size
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0, 0, 0, 5);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(itemQuantityLabel, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(itemQuantityField, gc);
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 2.0;// was 1.0
		// Button
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, -30, 0, 5);
		add(addOrderBtn, gc);
		// clear button
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, -115);
		add(clearBtn, gc);
		
		Border innerBorder = BorderFactory.createTitledBorder("Place your Order");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder , innerBorder));
		setBackground(Constants.maya);
		setPreferredSize(new Dimension(300,200));
	}

	public void setOrderListener(OrderListener orderListener) {
		this.orderListener = orderListener;
	}
	
}
