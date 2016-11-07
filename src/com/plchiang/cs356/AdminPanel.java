/*	AdminPanel
 * 	implements Singleton pattern
 */

package com.plchiang.cs356;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeSelectionModel;



public class AdminPanel extends JPanel {
//	single instance of this class running in the program
	private static AdminPanel INSTANCE;
	private static Color darkSlateGray = new Color(47, 79, 79);
	private JTree tree;
	private DefaultMutableTreeNode selectedNode;
	
//	singleton private constructor
	private AdminPanel() {
		//absolute positioning
		super(null);
		
		initComponents();
	}
	
	public synchronized static AdminPanel getInstance() {
		if(INSTANCE == null) {
			synchronized(AdminPanel.class) {
				if(INSTANCE == null) {
					INSTANCE = new AdminPanel();
				}
			}
		}
		return INSTANCE;
	}
	
	public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }
	
//	Initializes the components of the admin panel
	private void initComponents() {
		initTreePanel();
		
		JTextArea userIDArea = new JTextArea(), groupIDArea = new JTextArea();
		JButton addUserBtn = new JButton("Add User"), addGroupBtn = new JButton("Add Group");
		userIDArea.setBounds(370, 10, 200, 30);
		add(userIDArea);
		addUserBtn.setBounds(580, 10, 150, 30);
		addUserBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addNode(new UserNode(userIDArea.getText()));
				userIDArea.setText("");
				System.out.println("Add user " + userIDArea.getText());
			}
		});
		add(addUserBtn);
		
		groupIDArea.setBounds(370, 50, 200, 30);
		add(groupIDArea);
		addGroupBtn.setBounds(580, 50, 150, 30);
		addGroupBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				addNode(new UserGroupNode(groupIDArea.getText()));
				groupIDArea.setText("");
				System.out.println("Add group " + groupIDArea.getText());
			}
		});
		add(addGroupBtn);
		
		JButton userViewBtn = new JButton("Open User View");
		userViewBtn.setBounds(370,100,360,30);
		add(userViewBtn);
		
		JButton showUserTotBtn = new JButton("Show user total"), 
				showGroupTotBtn = new JButton("Show group total"),
				showMsgsTotBtn = new JButton("Show messages total"),
				showPosBtn = new JButton("Show positive percentage");
		showUserTotBtn.setBounds(370,520,200,30);
		add(showUserTotBtn);
		showGroupTotBtn.setBounds(580,520,200,30);
		add(showGroupTotBtn);
		showMsgsTotBtn.setBounds(370,560,200,30);
		add(showMsgsTotBtn);
		showPosBtn.setBounds(580,560,200,30);
		add(showPosBtn);
	}
	
	private void addNode(DefaultMutableTreeNode node) {
		DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
		try {
			model.insertNodeInto(node, selectedNode, selectedNode.getChildCount());
		} catch(NullPointerException | IllegalStateException e) {
//			case: user tried to add user/group to tree without selecting any node 
//			or selecting a UserNode (which can't have children)
			JOptionPane.showMessageDialog(SwingUtilities.getAncestorOfClass(JFrame.class, this), "Must select a group to add the node to.");
		}
	}
	
	private void initTreePanel() {
		JPanel treePanel = new JPanel();
		treePanel.setBounds(10,10,350,580);
		treePanel.setBorder(BorderFactory.createLineBorder(darkSlateGray));
		
		DefaultMutableTreeNode root = new UserGroupNode("Root");
		tree = new JTree(root);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
		    @Override
		    public void valueChanged(TreeSelectionEvent e) {
		    	selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		    	System.out.println("selected " + selectedNode);
		    }
		});
		
		DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
		
		
		DefaultMutableTreeNode group1 = new UserGroupNode("group1");
		model.insertNodeInto(new UserNode("stu1"), group1, group1.getChildCount());
		model.insertNodeInto(group1, root, root.getChildCount());
		
		UserNode stu0 = new UserNode("stu0");
		model.insertNodeInto(stu0, root, root.getChildCount());
		
		//initialize tree with nodes expanded
		for (int i = 0; i < tree.getRowCount(); i++) {
		    tree.expandRow(i);
		}
		
		treePanel.add(tree);
		add(treePanel);
	}

}
