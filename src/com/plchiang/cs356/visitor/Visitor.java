/*
 * implementation of the visitor interface from the visitor pattern
 */

package com.plchiang.cs356.visitor;

import javax.swing.tree.DefaultMutableTreeNode;

import com.plchiang.cs356.User;
import com.plchiang.cs356.composite.UserGroupNode;
import com.plchiang.cs356.composite.UserNode;

public interface Visitor {
	/*
	 * does something to a UserNode
	 * @param node the node to visit
	 */
	public void visitUserNode(UserNode node);
	
	/*
	 * does something to a UserGroupNode
	 * @param node the node to visit
	 */
	public void visitUserGroupNode(UserGroupNode node);
}
