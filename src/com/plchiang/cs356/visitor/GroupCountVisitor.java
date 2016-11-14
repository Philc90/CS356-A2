/*
 * Visitor class that counts the number of usergroups
 */

package com.plchiang.cs356.visitor;

import com.plchiang.cs356.composite.UserGroupNode;
import com.plchiang.cs356.composite.UserNode;

public class GroupCountVisitor extends CountVisitor {
	public GroupCountVisitor() {
		super();
	}

	@Override
	public void visitUserNode(UserNode node) {
		// do nothing
	}

	@Override
	public void visitUserGroupNode(UserGroupNode node) {
		super.count++;
	}

}
