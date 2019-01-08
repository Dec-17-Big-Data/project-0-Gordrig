package com.project0.dao;

import java.util.List;
import java.util.Optional;

import com.project0.models.User_Model;

public interface UserDao {
	Optional<List<User_Model>> getUsers();
	Optional<User_Model> getUserByID(long userID);
	void createUser(String username, String password);
}
