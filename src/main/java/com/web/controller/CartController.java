package com.web.controller;

import com.model.Books;
import com.service.BooksService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by bohdan on 01.09.16.
 */
@Controller
@RequestMapping("/cart")
public class CartController {
    private static final Logger logger = Logger.getLogger(CartController.class);
    @Autowired
    private BooksService booksService;

    @RequestMapping(value = "getAllBooksFromCart")
    public ModelAndView getAllBooks() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        String name =userDetail.getUsername();
        logger.info("Getting the all Book from Cart.");
        List<Books> booksCartList = booksService.getAllCartBooks(name);
        return new ModelAndView("cart", "booksCartList", booksCartList);
    }
    @RequestMapping(value = "deleteFromCart")
    public ModelAndView deleteFromCart() {

        return new ModelAndView("redirect:booksCartList");
    }
}
