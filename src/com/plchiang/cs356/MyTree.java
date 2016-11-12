package com.plchiang.cs356;

import java.util.Enumeration;

import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.plchiang.cs356.composite.MyTreeNode;
import com.plchiang.cs356.composite.UserGroupNode;
import com.plchiang.cs356.composite.UserNode;
import com.plchiang.cs356.visitor.Visitor;

public class MyTree extends JTree{
	MyTreeNode root, selectedNode;
	
	public MyTree(MyTreeNode root) {
		super(root);
		this.root = root;
		setCellRenderer(new MyRenderer());
		
		getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
		    
			@Override
		    public void valueChanged(TreeSelectionEvent e) {
		    	selectedNode = (MyTreeNode) getLastSelectedPathComponent();
		    	System.out.println("selected " + selectedNode);
		    }
			
		});
		
		getModel().addTreeModelListener(new TreeModelListener() {

			@Override
			public void treeNodesChanged(TreeModelEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void treeNodesInserted(TreeModelEvent arg0) {
				expandPath(arg0.getTreePath());
			}

			@Override
			public void treeNodesRemoved(TreeModelEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void treeStructureChanged(TreeModelEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		DefaultTreeModel model = (DefaultTreeModel)getModel();
		
		UserNode userSam = new UserNode("Sam");
		AdminFrame.getUserRecord().put(userSam.getUserID(), userSam.getUser());
		DefaultMutableTreeNode group1 = new UserGroupNode("group1");
		model.insertNodeInto(userSam, group1, group1.getChildCount());
		model.insertNodeInto(group1, this.root, this.root.getChildCount());
		
		UserNode userAlex = new UserNode("Alex");
		AdminFrame.getUserRecord().put("Alex", userAlex.getUser());
		userSam.subscribe(userAlex);
		model.insertNodeInto(userAlex, this.root, this.root.getChildCount());
		
		//initialize tree with nodes expanded
		for (int i = 0; i < getRowCount(); i++) {
		    expandRow(i);
		}
	}
	
	public DefaultMutableTreeNode getSelectedNode(){
		return selectedNode;
	}
	
	public void acceptNodeVisitor(Visitor visitor) {
		Enumeration<MyTreeNode> e = root.preorderEnumeration();
		while(e.hasMoreElements()) {
			e.nextElement().accept(visitor);
		}
	}
	
}
