package com.plchiang.cs356.composite;

import javax.swing.tree.DefaultMutableTreeNode;

import com.plchiang.cs356.visitor.Visitable;
import com.plchiang.cs356.visitor.Visitor;

public abstract class MyTreeNode extends DefaultMutableTreeNode implements Visitable {
	public MyTreeNode(String name) {
		super(name);
	}

	@Override
	public abstract void accept(Visitor visitor);
}
