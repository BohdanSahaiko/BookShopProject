package com.web.controller;


import com.service.UsersService;
import com.model.Users;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/users**")
class UsersController {
	
	private static final Logger logger = Logger.getLogger(UsersController.class);
	
	public UsersController() {
		System.out.println("UserController()");
	}

    @Autowired
    private UsersService usersService;

    @RequestMapping(value = {"getAllUsers", "/"})
    public ModelAndView getAllEmployees() {
    	logger.info("Getting the all Users.");
        List<Users> usersList = usersService.getAllUsers();
        return new ModelAndView("usersList", "usersList", usersList);
    }
}