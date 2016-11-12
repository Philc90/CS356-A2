package com.plchiang.cs356.visitor;

import com.plchiang.cs356.composite.UserGroupNode;
import com.plchiang.cs356.composite.UserNode;

public abstract class CountVisitor implements Visitor {
	protected int count;
	
	public CountVisitor() {
		count = 0;
	}
	
	@Override
	public abstract void visitUserNode(UserNode node);
	
	@Override
	public abstract void visitUserGroupNode(UserGroupNode node);
	
	public int getCount() {
		return count;
	}
}
