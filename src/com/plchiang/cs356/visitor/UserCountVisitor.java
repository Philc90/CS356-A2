package com.plchiang.cs356.visitor;

import com.plchiang.cs356.composite.UserGroupNode;
import com.plchiang.cs356.composite.UserNode;

public class UserCountVisitor extends CountVisitor {
	public UserCountVisitor() {
		super();
	}
	
	public int getCount() {
		return count;
	}

	@Override
	public void visitUserNode(UserNode node) {
		count++;
	}

	@Override
	public void visitUserGroupNode(UserGroupNode node) {
		// do nothing
	}

}
