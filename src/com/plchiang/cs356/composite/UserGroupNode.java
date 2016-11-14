/*	UserGroupNode
 *  Composite pattern - UserGroupNode is a child of MyTreeNode class and is the composite version
 */

package com.plchiang.cs356.composite;

import javax.swing.tree.DefaultMutableTreeNode;

import com.plchiang.cs356.visitor.Visitable;
import com.plchiang.cs356.visitor.Visitor;

public class UserGroupNode extends MyTreeNode implements Visitable{
	private String groupID;
	
	public UserGroupNode(String groupID) {
		super(groupID);
		this.groupID = groupID;
		super.allowsChildren = true;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitUserGroupNode(this);
	}
}
