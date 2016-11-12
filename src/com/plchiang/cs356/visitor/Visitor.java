package com.plchiang.cs356.visitor;

import javax.swing.tree.DefaultMutableTreeNode;

import com.plchiang.cs356.User;
import com.plchiang.cs356.composite.UserGroupNode;
import com.plchiang.cs356.composite.UserNode;

public interface Visitor {
	public void visitUserNode(UserNode node);
	public void visitUserGroupNode(UserGroupNode node);
}
