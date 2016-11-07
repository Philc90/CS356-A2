package com.plchiang.cs356.test;

import com.plchiang.cs356.User;

public class ComponentTester {

	public static void main(String[] args) {
		User sammy = new User("01111"), johnny = new User("011232"), iggy = new User("12345");
		sammy.attach(johnny);
		sammy.attach(iggy);
		johnny.attach(sammy);
		
		sammy.broadcast("Sup y'all");
		johnny.broadcast("yo");
	}

}
