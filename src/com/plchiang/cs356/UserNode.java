/*	UserNode
 *  Uses Composite pattern - UserNode is a child of DefaultMutableTreeNode class and is the leaf version
 *  Is a wrapper for User class in order to use it in JTree.
 */

package com.plchiang.cs356;

import javax.swing.tree.DefaultMutableTreeNode;

public class UserNode extends DefaultMutableTreeNode {
	private User myUser;
	
	public UserNode(String userID) {
		super(userID);
		myUser = new User(userID);
		super.allowsChildren = false;
	}
	
	public void broadcast(String tweet) {
		myUser.broadcast(tweet);
	}
	
	public void subscribe(UserNode user) {
		myUser.subscribe(user.getUser());
	}
	
	public void unsubscribe(UserNode user) {
		myUser.unsubscribe(user.getUser());
	}
	
	public String getUserID() {
		return myUser.getUserID();
	}
	
	public User getUser() {
		return myUser;
	}
}
