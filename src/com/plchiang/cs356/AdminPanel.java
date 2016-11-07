package com.plchiang.cs356;

import javax.swing.JPanel;

public class AdminPanel extends JPanel {
	private static AdminPanel INSTANCE;
	private AdminPanel() {
		super();
		
	}
	
	public synchronized static AdminPanel getInstance() {
		if(INSTANCE == null) {
			synchronized(AdminPanel.class) {
				if(INSTANCE == null) {
					INSTANCE = new AdminPanel();
				}
			}
		}
		return INSTANCE;
	}
	
}
