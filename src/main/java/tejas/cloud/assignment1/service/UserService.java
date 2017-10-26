package tejas.cloud.assignment1.service;

import java.util.ArrayList;
import java.util.List;

import tejas.cloud.assignment1.model.User;

public class UserService {

	public static List<User> users;

	public void setUser() {
		users = new ArrayList<User>();

		User u1 = new User(1234, "tejas");
		User u2 = new User(1111, "cloud");

		users.add(u1);
		users.add(u2);
	}	
	
}
