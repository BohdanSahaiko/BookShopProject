package com.web.controller;


import com.model.Books;
import com.model.Users;
import com.service.BooksService;
import com.service.UsersService;
import org.hibernate.NonUniqueObjectException;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
class BooksController {

    private static final Logger logger = Logger.getLogger(BooksController.class);

    public BooksController() {
        System.out.println("BookController()");
    }

    @Autowired
    private UsersService usersService;
    @Autowired
    private BooksService booksService;

    @RequestMapping("createUser")
    public ModelAndView createUser(@ModelAttribute Users users) {
        logger.info("Creating User. Data: " + users);
        return new ModelAndView("usersForm");
    }
    @RequestMapping("addUser")
    public ModelAndView add(@RequestParam String username, @RequestParam String password) {
        usersService.addUser(username, password);
        return new ModelAndView("login");
    }
    @RequestMapping(value = "createBook")
    public ModelAndView createBook(@ModelAttribute Books books) {
        logger.info("Creating Book. Data: " + books);
        return new ModelAndView("booksForm");
    }
    @RequestMapping("editBooks")
    public ModelAndView editBooks(@RequestParam long id, @ModelAttribute Books books) {
        logger.info("Updating the book for the Id " + id);
        books = booksService.getBook(id);
        return new ModelAndView("booksForm", "bookObject", books);
    }
    @RequestMapping("saveBook")
    public ModelAndView saveBooks(@ModelAttribute Books books) {

        logger.info("Saving the Books. Data : " + books);
        if (books.getId() == 0) {
            booksService.createBook(books);
        } else {
            booksService.updateBook(books);
        }
        return new ModelAndView("redirect:getAllBooks");
    }
    @RequestMapping("deleteBooks")
    public ModelAndView deleteBooks(@RequestParam Integer id) {
        logger.info("Deleting the Book. Id : " + id);
        booksService.deleteBook(id);
        return new ModelAndView("redirect:getAllBooks");
    }
    @RequestMapping(value = {"getAllBooks", "/"})
    public ModelAndView getAllBooks() {
        logger.info("Getting the all Book.");
        List<Books> booksList = booksService.getAllBooks();
        return new ModelAndView("booksList", "booksList", booksList);
    }

    @RequestMapping(value = "searchBook",method = RequestMethod.GET,headers="Accept=application/json")
    @ResponseBody
    public List<Books> searchBook(@RequestParam("searchName") String searchName) {
        logger.info("Searching the Book. Book Names: " + searchName);
        List<Books> booksList = booksService.getAllBooks(searchName);
        return booksList;
    }
        @RequestMapping(value = "addBook" , method = RequestMethod.GET)
        public @ResponseBody
        ModelAndView addToCart(@RequestParam Integer id) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            String name =userDetail.getUsername();
            logger.info("username:  " + name);
            Books book = booksService.getBook(id);
            Users users = usersService.findByUserName(name);
            usersService.addBook(users,book);
            return new ModelAndView("redirect:getAllBooks");
        }
}