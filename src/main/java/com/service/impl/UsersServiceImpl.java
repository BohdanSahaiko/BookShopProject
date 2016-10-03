package com.service.impl;

import com.dao.UsersDao;
import com.model.Books;
import com.service.UsersService;

import com.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

    public UsersServiceImpl() {
        System.out.println("usersServiceImpl()");
    }

    @Autowired
    private UsersDao usersDao;

    @Override
    public List<Users> getAllUsers() {
        return usersDao.getAllUsers();
    }

    @Override
    public void addUser(String username, String password) {
        usersDao.addUser(username,password);
    }

    @Override
    public void addBook(Users user, Books book) {
        usersDao.addBook(user,book);
    }

    @Override
    public Users findByUserName(String username) {
        return usersDao.findByUserName(username);

    }

}