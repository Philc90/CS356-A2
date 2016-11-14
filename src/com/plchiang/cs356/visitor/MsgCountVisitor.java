/*
 * Visitor class that counts number of messages in each user's newsfeed
 */

package com.plchiang.cs356.visitor;

import com.plchiang.cs356.composite.UserGroupNode;
import com.plchiang.cs356.composite.UserNode;

public class MsgCountVisitor extends CountVisitor {
	public MsgCountVisitor() {
		super();
	}

	@Override
	public void visitUserNode(UserNode node) {
		super.count += node.getUser().getNewsfeedModel().size();
	}

	@Override
	public void visitUserGroupNode(UserGroupNode node) {
		// do nothing
	}

}
