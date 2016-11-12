/* UserFrame
 * JFrame wrapper class for UserPanel
 */

package com.plchiang.cs356;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.plchiang.cs356.composite.UserNode;

public class UserFrame extends JFrame {
	private User user;
	private JPanel userPanel;
	private Hashtable<String, User> userRecord;
	
	public UserFrame(UserNode userNode) {
		user = userNode.getUser();
		userRecord  = AdminFrame.getUserRecord();
		setTitle(user.getUserID() + "'s Control Panel");
		initComponents();
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
//	Initializes the gui components of the user panel
	private void initComponents() {
		userPanel = new JPanel();
		userPanel.setLayout(null);
		userPanel.setPreferredSize(new Dimension(300, 500));
		
		JTextField userIDField = new JTextField("");
		userIDField.setBounds(10,10,150,30);
		userPanel.add(userIDField);
		
		DefaultListModel<String> followingModel = user.getFollowingModel();
		JButton followBtn = new JButton("Follow User");
		followBtn.setBounds(170,10,120,30);
		followBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String userID = userIDField.getText();
				User userToFollow = userRecord.get(userID);
				if(userToFollow != null && !user.getUserID().equals(userID)) {
					user.subscribe(userToFollow);
					JOptionPane.showMessageDialog(SwingUtilities.getAncestorOfClass(JFrame.class, userPanel), "Subscribed to " + userIDField.getText() + ".");
				} else {
					JOptionPane.showMessageDialog(SwingUtilities.getAncestorOfClass(JFrame.class, userPanel), "Can't subscribe to that user.");
				}
				userIDField.setText("");
			}
		});
		userPanel.add(followBtn);
	
		JLabel followingLabel = new JLabel("Currently Following");
		followingLabel.setBounds(10,50,150,30);
		userPanel.add(followingLabel);
		
		JList<String> followingList = new JList<>();
		
		followingList.setModel(followingModel);
		followingList.setLayoutOrientation(JList.VERTICAL);
		followingList.setVisibleRowCount(-1);
		
		JScrollPane followingPane = new JScrollPane(followingList);
		followingPane.setBounds(10,80,280,105);
		userPanel.add(followingPane);
		
		JTextField messageField = new JTextField("");
		messageField.setBounds(10,205,150,30);
		userPanel.add(messageField);
		
		
		DefaultListModel<String> newsfeedModel = user.getNewsfeedModel();
		JButton postBtn = new JButton("Post Tweet");
		postBtn.setBounds(170,205,120,30);
		postBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				String tweet = messageField.getText();
				messageField.setText("");
				user.broadcast(tweet);
				newsfeedModel.addElement("You: " + tweet);
			} 
			
		});
		userPanel.add(postBtn);
		
		JLabel newsfeedLabel = new JLabel("Newsfeed");
		newsfeedLabel.setBounds(10,245,150,30);
		userPanel.add(newsfeedLabel);
		
		JList<String> newsfeedList = new JList<>();
		
		newsfeedList.setModel(newsfeedModel);
		JScrollPane newsfeedPane = new JScrollPane(newsfeedList);
		newsfeedPane.setBounds(10,275,280,205);
		newsfeedList.setLayoutOrientation(JList.VERTICAL);
		newsfeedList.setVisibleRowCount(-1);
		userPanel.add(newsfeedPane);
		
		add(userPanel);
	}

}
