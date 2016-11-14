/*
 * MyTreeNode
 * This class is used as the abstract base class for UserGroupNodes and UserNode.
 * It implements the Composite pattern.
 * It inherits from DefaultMutableTreeNode so that it can be used with MyTree which is a JTree.
 * It also implements Visitable so its child classes (UserGroupNode and UserNode) will be visitable by
 * the Visitor classes.
 */

package com.plchiang.cs356.composite;

import javax.swing.tree.DefaultMutableTreeNode;

import com.plchiang.cs356.visitor.Visitable;
import com.plchiang.cs356.visitor.Visitor;

public abstract class MyTreeNode extends DefaultMutableTreeNode implements Visitable {
	public MyTreeNode(String name) {
		super(name);
	}
	/*
	 * (non-Javadoc)
	 * @see com.plchiang.cs356.visitor.Visitable#accept(com.plchiang.cs356.visitor.Visitor)
	 */
	@Override
	public abstract void accept(Visitor visitor);
}
