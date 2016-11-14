/*
 * implementation of the Subject class from the Observer pattern
 */
package com.plchiang.cs356.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {
	protected List<Observer> observers;
	
	public Subject() {
		observers = new ArrayList<>();
	}
	
	protected void attach(Observer obs) {
		observers.add(obs);
	}
	protected void detach(Observer obs) {
		observers.remove(obs);
	}
	protected void notifyObservers() {
		for(Observer obs: observers) {
			obs.update(this);
		}
	}
}
