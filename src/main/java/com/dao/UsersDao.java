package com.dao;


import com.model.Books;
import com.model.Users;

import java.util.List;


public interface UsersDao {
     List<Users> getAllUsers();
     Users findByUserName(String username);
     void addUser(String username, String password);
     void addBook(Users user, Books book);
}