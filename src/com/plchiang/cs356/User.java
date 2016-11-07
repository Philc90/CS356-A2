/* User
 * Observer pattern - User is both a Subject since it can send messages to subscribers, and an
 * 					Observer since it can subscribe to other users.
 */

package com.plchiang.cs356;

import java.util.ArrayList;
import java.util.List;

import com.plchiang.cs356.observer.*;

public class User extends Subject implements Observer{
	private String userID;
	private List<Observer> followers, followings;
	private String msg;
	
	public User(String userID) {
		super();
		this.userID = userID;
		followers = super.observers;
		followings = new ArrayList<Observer>();
		msg = "";
	}
	
	public String getUserID() {
		return userID;
	}
	
	public String getMessage() {
		return msg;
	}
	
	public void broadcast(String msg) {
		this.msg = msg;
		notifyObservers();
	}
	
	@Override
	public void attach(Observer obs) {
		//ignore subscribing to oneself
		if(obs != this) {
			observers.add(obs);
		}
	}
	
	@Override
	public void detach(Observer obs) {
		//ignore subscribing to oneself
		if(obs != this) {
			observers.remove(obs);
		}
	}
	
	@Override
	public void update(Subject s) {
		User user = ((User) s);
		System.out.println(user.getUserID() + ": " + user.getMessage());
	}
	
}
