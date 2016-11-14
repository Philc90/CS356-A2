/*	UserNode
 *  Uses Composite pattern - UserNode is a child of MyTreeNode class and is the leaf version
 *  Is a wrapper for User class so that it can be used in a JTree.
 */

package com.plchiang.cs356.composite;

import com.plchiang.cs356.User;
import com.plchiang.cs356.visitor.Visitable;
import com.plchiang.cs356.visitor.Visitor;

public class UserNode extends MyTreeNode implements Visitable {
	private User myUser;
	
	public UserNode(String userID) {
		super(userID);
		myUser = new User(userID);
		super.allowsChildren = false;
	}
	
	/*
	 * sends tweet to other users' newsfeeds
	 */
	public void broadcast(String tweet) {
		myUser.broadcast(tweet);
	}
	
	/*
	 * Subscribes to another user
	 */
	public void subscribe(UserNode user) {
		myUser.subscribe(user.getUser());
	}
	
	/*
	 * unsubscribes from the other user
	 */
	public void unsubscribe(UserNode user) {
		myUser.unsubscribe(user.getUser());
	}
	
	/*
	 * accessor for the myUser member var.
	 * @return myUser's userID
	 */
	public String getUserID() {
		return myUser.getUserID();
	}
	
	/*
	 * accessor for the myUser member variable
	 * @return myUser
	 */
	public User getUser() {
		return myUser;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.plchiang.cs356.composite.MyTreeNode#accept(com.plchiang.cs356.visitor.Visitor)
	 */
	@Override
	public void accept(Visitor visitor) {
		visitor.visitUserNode(this);
	}
}
