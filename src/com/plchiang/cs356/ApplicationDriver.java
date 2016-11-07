package com.plchiang.cs356;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ApplicationDriver{

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}
	
	private static void createAndShowGUI() {
		JFrame adminFrame = new JFrame("Admin Panel");
		adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		adminFrame.add(AdminPanel.getInstance());
		adminFrame.pack();
		adminFrame.setVisible(true);
	}

}
