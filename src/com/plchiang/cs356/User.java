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
		followers = super.observers;
		followings = new HashSet<>();
		followingModel = new DefaultListModel<>();
		newsfeedModel = new DefaultListModel<>();
		msg = "";
	}
	
	public String getUserID() {
		return userID;
	}
	
	public String getMessage() {
		return msg;
	}
	
	public void broadcast(String tweet) {
		this.msg = userID + ": " + tweet;
		notifyObservers();
	}
	
	public void subscribe(User user) {
		//ignore subscribing to oneself or already subscribed
		if(user != this && !followings.contains(user)) {
			user.attach(this);
			followings.add(user);
			followingModel.addElement(user.userID);
		}
	}
	
	public void unsubscribe(User user) {
		//ignore subscribing to oneself or already subscribed
		if(user != this && !followings.contains(user)) {
			user.detach(this);
			followings.remove(user);
			followingModel.removeElement(user.userID);
		}
	}
	
	public DefaultListModel<String> getNewsfeedModel() {
		return newsfeedModel;
	}
	
	public DefaultListModel<String> getFollowingModel() {
		return followingModel;
	}
	
	@Override
	public void update(Subject s) {
		User user = ((User) s);
		System.out.println(user.getMessage());
		if(newsfeedModel != null) {
			newsfeedModel.addElement(user.getMessage());
		} else {
			System.out.println("Warning: no model set for user " + userID);
		}
		
	}
	
}
