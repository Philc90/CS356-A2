package com.plchiang.cs356.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {
	protected List<Observer> observers = new ArrayList<>();
	
	public void attach(Observer obs) {
		observers.add(obs);
	}
	public void detach(Observer obs) {
		observers.remove(obs);
	}
	protected void notifyObservers() {
		for(Observer obs: observers) {
			obs.update(this);
		}
	}
}
