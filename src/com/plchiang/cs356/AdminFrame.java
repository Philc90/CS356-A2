package com.plchiang.cs356;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.plchiang.cs356.composite.MyTreeNode;
import com.plchiang.cs356.composite.UserGroupNode;
import com.plchiang.cs356.composite.UserNode;
import com.plchiang.cs356.visitor.GroupCountVisitor;
import com.plchiang.cs356.visitor.MsgCountVisitor;
import com.plchiang.cs356.visitor.PosWordCountVisitor;
import com.plchiang.cs356.visitor.UserCountVisitor;

public class AdminFrame extends JFrame {
//	single instance of this class running in the program
	private static AdminFrame INSTANCE = null;
	private static Hashtable<String, User> userRecord;
	private static Hashtable<String, UserGroupNode> userGroupRecord;
	private static JPanel adminPanel;
	private static final Color darkSlateGray = new Color(47, 79, 79);
	private static MyTree tree;
//	private static DefaultMutableTreeNode selectedNode;

//	singleton private constructor
	private AdminFrame() {
		super("Admin Control Panel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		userRecord = new Hashtable<>();
		userGroupRecord = new Hashtable<>();
		initComponents();
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	public synchronized static AdminFrame getInstance() {
		if(INSTANCE == null) {
			synchronized(AdminFrame.class) {
				if(INSTANCE == null) {
					INSTANCE = new AdminFrame();
				}
			}
		}
		return INSTANCE;
	}
	
	public static Hashtable<String, User> getUserRecord() {
		return userRecord;
	}
	
//	Initializes the gui components of the admin panel
	private void initComponents() {
		adminPanel = new JPanel();
		adminPanel.setPreferredSize(new Dimension(800, 600));
		adminPanel.setLayout(null);

		initTreePanel();
		
		JTextField userIDField = new JTextField();
		JButton addUserBtn = new JButton("Add User"), addGroupBtn = new JButton("Add Group");
		userIDField.setBounds(370, 10, 200, 30);
		adminPanel.add(userIDField);
		addUserBtn.setBounds(580, 10, 150, 30);
		addUserBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String userID = userIDField.getText();
				
				if(userRecord.containsKey(userID)) {
					JOptionPane.showMessageDialog(SwingUtilities.getAncestorOfClass(JFrame.class, adminPanel), userID + " is already taken.");
				} else if(!userID.isEmpty()) {
					UserNode newUserNode = new UserNode(userID);
					if(addNode(newUserNode)) {
						userRecord.put(userID, newUserNode.getUser());
					}
				}
				userIDField.setText("");
				System.out.println("Added user " + userIDField.getText());
			}
		});
		adminPanel.add(addUserBtn);
		
		JTextField groupIDField = new JTextField("");
		groupIDField.setBounds(370, 50, 200, 30);
		adminPanel.add(groupIDField);
		addGroupBtn.setBounds(580, 50, 150, 30);
		addGroupBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String groupID = groupIDField.getText();
				if(userGroupRecord.containsKey(groupID)) {
					JOptionPane.showMessageDialog(SwingUtilities.getAncestorOfClass(JFrame.class, adminPanel), groupID + " is already taken.");
				} else if(!groupID.isEmpty()) {
					UserGroupNode newUserGroupNode = new UserGroupNode(groupID);
					if(addNode(newUserGroupNode)) {
						userGroupRecord.put(groupID, newUserGroupNode);
					}
				}
				groupIDField.setText("");
				System.out.println("Added group " + groupIDField.getText());
			}
		});
		adminPanel.add(addGroupBtn);
		
		JButton userViewBtn = new JButton("Open User View");
		userViewBtn.setBounds(370,100,360,30);
		userViewBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//protects against non-selected or group node
				if(tree.getSelectedNode() != null && tree.getSelectedNode() instanceof UserNode) {
					new UserFrame((UserNode) tree.getSelectedNode());
				}
			}
			
		});
		adminPanel.add(userViewBtn);
		
		JButton showUserTotBtn = new JButton("Show user total"), 
				showGroupTotBtn = new JButton("Show group total"),
				showMsgsTotBtn = new JButton("Show messages total"),
				showPosBtn = new JButton("Show positive percentage");
		showUserTotBtn.setBounds(370,520,200,30);
		showUserTotBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				UserCountVisitor userCountVisitor = new UserCountVisitor();
				tree.acceptNodeVisitor(userCountVisitor);
				JOptionPane.showMessageDialog(SwingUtilities.getAncestorOfClass(JFrame.class, adminPanel), "# of Users: " + userCountVisitor.getCount());
			}
			
		});
		adminPanel.add(showUserTotBtn);
		
		showGroupTotBtn.setBounds(580,520,200,30);
		showGroupTotBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GroupCountVisitor groupCountVisitor = new GroupCountVisitor();
				tree.acceptNodeVisitor(groupCountVisitor);
				JOptionPane.showMessageDialog(SwingUtilities.getAncestorOfClass(JFrame.class, adminPanel), "# of Groups: " + groupCountVisitor.getCount());
			}
			
		});
		adminPanel.add(showGroupTotBtn);
		
		showMsgsTotBtn.setBounds(370,560,200,30);
		showMsgsTotBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MsgCountVisitor msgCountVisitor = new MsgCountVisitor();
				tree.acceptNodeVisitor(msgCountVisitor);
				JOptionPane.showMessageDialog(SwingUtilities.getAncestorOfClass(JFrame.class, adminPanel), "# of Messages: " + msgCountVisitor.getCount());
			}
		});
		adminPanel.add(showMsgsTotBtn);
		
		showPosBtn.setBounds(580,560,200,30);
		showPosBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PosWordCountVisitor posWordCountVisitor = new PosWordCountVisitor();
				tree.acceptNodeVisitor(posWordCountVisitor);
				MsgCountVisitor msgCountVisitor = new MsgCountVisitor();
				tree.acceptNodeVisitor(msgCountVisitor);
				int numPosWords = posWordCountVisitor.getCount(), numWords = msgCountVisitor.getCount();
				double positivity = (double) numPosWords / (double) numWords * 100;
				JOptionPane.showMessageDialog(SwingUtilities.getAncestorOfClass(JFrame.class, adminPanel), "Overall Positivity: " + positivity + "%");
			}
		});
		adminPanel.add(showPosBtn);
		
		add(adminPanel);
	}
	
	private boolean addNode(MyTreeNode node) {
		DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
		MyTreeNode root = (MyTreeNode) model.getRoot();
		try {
			model.insertNodeInto(node, tree.getSelectedNode(), tree.getSelectedNode().getChildCount());
		} catch(NullPointerException | IllegalStateException e) {
//			case: user tried to add user/group to tree without selecting any node 
//			or selecting a UserNode (which can't have children)
			JOptionPane.showMessageDialog(SwingUtilities.getAncestorOfClass(JFrame.class, this), "Must select a group to add the node to.");
			return false;
		}
		return true;
	}
	
	private void initTreePanel() {
		JPanel treePanel = new JPanel();
		treePanel.setBounds(10,10,350,580);
		treePanel.setBorder(BorderFactory.createLineBorder(darkSlateGray));
		
//		DefaultMutableTreeNode root = new UserGroupNode("Root");
//		tree = new JTree(root);
//		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
//		tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
//		    
//			@Override
//		    public void valueChanged(TreeSelectionEvent e) {
//		    	selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
//		    	System.out.println("selected " + selectedNode);
//		    }
//			
//		});
//		
//		DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
//		
//		UserNode userSam = new UserNode("Sam");
//		userRecord.put(userSam.getUserID(), userSam.getUser());
//		DefaultMutableTreeNode group1 = new UserGroupNode("group1");
//		model.insertNodeInto(userSam, group1, group1.getChildCount());
//		model.insertNodeInto(group1, root, root.getChildCount());
//		
//		UserNode userAlex = new UserNode("Alex");
//		userRecord.put("Alex", userAlex.getUser());
//		userSam.subscribe(userAlex);
//		model.insertNodeInto(userAlex, root, root.getChildCount());
//		
//		//initialize tree with nodes expanded
//		for (int i = 0; i < tree.getRowCount(); i++) {
//		    tree.expandRow(i);
//		}
		
		MyTreeNode root = new UserGroupNode("Root");
		tree = new MyTree(root);
		treePanel.add(tree);
		adminPanel.add(treePanel);
	}
}
