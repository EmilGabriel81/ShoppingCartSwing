package com.shopgui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.shopgui.constants.Constants;
import com.shopgui.objects.*;



public class OrderTablePanel extends JPanel{
	
	private JTable table;
	private OrderTableModel orderTableModel;
	
	
	public OrderTablePanel() {
		//setBackground();
		orderTableModel = new OrderTableModel();
		table = new JTable(orderTableModel);
		
		setLayout(new BorderLayout());
		add(new JScrollPane(table) , BorderLayout.CENTER);
		setBackground(Constants.maya);
		setPreferredSize(new Dimension(600,200));
		
	}
	
	public void setData(List<Item> db) {
		orderTableModel.setData(db);
	}
	
	public void refresh() {
		orderTableModel.fireTableDataChanged();
	}
}
