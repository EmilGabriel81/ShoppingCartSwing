package com.shopgui.view;

import javax.swing.SwingUtilities;

public class StartApp {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				System.out.println("--------------------------------------------------------");
				new MainShop();
				System.out.println("--------------------------------------------------------");
			}
		});

	}

}
