/*	UserNode
 *  Uses Composite pattern - UserNode is a child of DefaultMutableTreeNode class and is the leaf version
 *  Is a wrapper for User class in order to use it in JTree.
 */

package com.plchiang.cs356;

import javax.swing.tree.DefaultMutableTreeNode;

public class UserNode extends DefaultMutableTreeNode {
	private User user;
	
	public UserNode(String userID) {
		super(userID);
		user = new User(userID);
		super.allowsChildren = false;
	}
	
	public void broadcast(String msg) {
		user.broadcast(msg);
	}
	
	public String getUserID() {
		return user.getUserID();
	}
}
