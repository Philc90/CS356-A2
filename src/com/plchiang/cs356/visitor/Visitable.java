/*
 * implementation of the visitable interface from the visitor pattern
 */

package com.plchiang.cs356.visitor;

public interface Visitable {
	/*
	 * defines the accept function that the visitor will call when reaching this object
	 * @param visitor the visitor that is visiting
	 */
	public void accept(Visitor visitor);
}
