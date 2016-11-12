package com.plchiang.cs356.visitor;

import java.util.HashSet;
import java.util.StringTokenizer;

import javax.swing.ListModel;

import com.plchiang.cs356.composite.UserGroupNode;
import com.plchiang.cs356.composite.UserNode;

public class PosWordCountVisitor extends CountVisitor {
	private static HashSet<String> positiveWords;
	
	public PosWordCountVisitor() {
		super();
		positiveWords = new HashSet<>();
		positiveWords.add("nice");
		positiveWords.add("happy");
		positiveWords.add("good");
		positiveWords.add("great");
		positiveWords.add("fantastic");
		
	}

	@Override
	public void visitUserNode(UserNode node) {
		super.count += positiveTweets(node.getUser().getNewsfeedModel());
	}

	@Override
	public void visitUserGroupNode(UserGroupNode node) {
		// do nothing
	}
	
	private int positiveTweets(ListModel<String> userModel) {
		int posWordCount = 0;
		for(int i = 0; i<userModel.getSize(); i++) {
			if(isPositive(userModel.getElementAt(i).toString())) {
				posWordCount++;
			}
		}
		return posWordCount;
	}
	
	private boolean isPositive(String tweet) {
		String[] words = tweet.split("\\s");
		for(String word : words) {
			if(positiveWords.contains(word.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

}
