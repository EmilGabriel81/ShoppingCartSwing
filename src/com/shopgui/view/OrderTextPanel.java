package com.shopgui.view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class OrderTextPanel extends JPanel {
	
	private JTextArea textArea;
	
	public OrderTextPanel() {
		textArea = new JTextArea();
		textArea.setSize(100,400);
		textArea.setFont(new Font("Helvetica", Font.BOLD, 16));
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(900,200));
		add(new JScrollPane(textArea), BorderLayout.CENTER);
		
	}
	
	public void appendText(String text) {
		textArea.append(text);
	}
	
	public void clearTextArea() {
		textArea.setText("");
	}
}
