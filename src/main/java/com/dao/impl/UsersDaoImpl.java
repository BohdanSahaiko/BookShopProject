package com.dao.impl;


import com.dao.UsersDao;
import com.model.Books;
import com.model.Users;
import com.util.SessionClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsersDaoImpl implements UsersDao {

    public UsersDaoImpl() {
        System.out.println("usersDAOImpl");
    }

    @Autowired
    private SessionClass sessionClass;

    @Override
    public List<Users> getAllUsers() {
        return sessionClass.fetchAll(Users.class);
    }

    @SuppressWarnings("unchecked")
    public Users findByUserName(String username) {
        return sessionClass.findByUserName(username);
    }

    @Override
    public void addUser(String username, String password) {
        sessionClass.addUser(username,password);
    }

    @Override
    public void addBook(Users user ,Books book) {
        sessionClass.addBookToUser(user ,book);
    }

}