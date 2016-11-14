/* User
 * Observer pattern - User is both a Subject since it can send messages to subscribers, and an
 * 					Observer since it can subscribe to other users.
 */

package com.plchiang.cs356;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultListModel;

import com.plchiang.cs356.observer.*;
import com.plchiang.cs356.visitor.Visitable;
import com.plchiang.cs356.visitor.Visitor;

public class User extends Subject implements Observer {
	private String userID;
	private List<Observer> followers;
	private HashSet<Observer> followings;
	private String msg;
	private DefaultListModel<String> followingModel, newsfeedModel;
	
	public User(String userID) {
		super();
		this.userID = userID;
		//followers are the observers of this object, inherited from Subject class
		followers = super.observers;
		//followings keeps track of what users already subscribed to so you don't subscribe twice.
		followings = new HashSet<>();
		//followingModel keeps track of all the users this user is following,
		//		and is necessary for updating the JList that displays the user's following list in the GUI.
		followingModel = new DefaultListModel<>();
		//newsfeedModel keeps track of all the tweets posted to the user's newsfeed,
		//		and is necessary for updating the JList that displays the user's newsfeed in the GUI.
		newsfeedModel = new DefaultListModel<>();
		msg = "";
	}
	
	/*
	 * accessor for userID
	 */
	public String getUserID() {
		return userID;
	}
	
	/*
	 * accessor for the user's most current message
	 */
	public String getMessage() {
		return msg;
	}
	
	/*
	 * accessor for newsfeedModel.
	 */
	public DefaultListModel<String> getNewsfeedModel() {
		return newsfeedModel;
	}
	
	/*
	 * accessor for followingModel
	 */
	public DefaultListModel<String> getFollowingModel() {
		return followingModel;
	}
	
	/*
	 * posts tweet to followers' newsfeed
	 */
	public void broadcast(String tweet) {
		this.msg = userID + ": " + tweet;
		notifyObservers();
	}
	
	/*
	 * subscribes to the other user
	 */
	public void subscribe(User user) {
		//ignore subscribing to oneself or already subscribed
		if(user != this && !followings.contains(user)) {
			user.attach(this);
			followings.add(user);
			followingModel.addElement(user.userID);
		}
	}
	
	/*
	 * unsubscribes from the other user
	 */
	public void unsubscribe(User user) {
		//ignore unsubscribing to oneself
		if(user != this) {
			user.detach(this);
			followings.remove(user);
			followingModel.removeElement(user.userID);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.plchiang.cs356.observer.Observer#update(com.plchiang.cs356.observer.Subject)
	 */
	@Override
	public void update(Subject s) {
		User user = ((User) s);
		System.out.println(user.getMessage());
		//upon receiving a new message, update the newsfeed in the GUI.
		newsfeedModel.addElement(user.getMessage());
		
	}
	
}
