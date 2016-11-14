/*
 * ApplicationDriver
 * The entry point for the program
 */

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
		JFrame adminFrame = AdminFrame.getInstance();
		adminFrame.setVisible(true);
	}

}
