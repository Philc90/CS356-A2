/*	UserGroupNode
 *  Composite pattern - UserGroupNode is a child of DefaultMutableTreeNode class and is the composite version
 */

package com.plchiang.cs356;

import javax.swing.tree.DefaultMutableTreeNode;

public class UserGroupNode extends DefaultMutableTreeNode {
	private String groupID;
	
	public UserGroupNode(String groupID) {
		super(groupID);
		this.groupID = groupID;
		super.allowsChildren = true;
	}
}
