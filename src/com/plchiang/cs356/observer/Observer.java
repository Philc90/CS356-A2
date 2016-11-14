/*
 * implementation of the Observer class of the Observer pattern
 */

package com.plchiang.cs356.observer;

public interface Observer {
	/*
	 * does the update function of the class
	 * @param s the subject it needs to get updates from
	 */
	public void update(Subject s);
}
