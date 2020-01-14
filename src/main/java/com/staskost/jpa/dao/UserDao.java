package com.staskost.jpa.dao;

import java.util.List;

import com.staskost.jpa.model.User;

public interface UserDao {
	
	void addUser(User user);
	
	User getUserById(int id);
	
	List<User> getUsers();
	
	void deleteUser(int id);
	
	void updateUser(int id, User user);

}
