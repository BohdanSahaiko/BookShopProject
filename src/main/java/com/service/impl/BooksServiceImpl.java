package com.service.impl;

import com.dao.BooksDao;
import com.service.BooksService;

import com.model.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bohdan on 13.08.16.
 */
@Service
@Transactional
public class BooksServiceImpl implements BooksService{

    public BooksServiceImpl(){}
    @Autowired
    private BooksDao booksDao;
    @Override
    public long createBook(Books book) {
        return booksDao.createBook(book);
    }

    @Override
    public Books updateBook(Books book) {
        return booksDao.updateBook(book);
    }

    @Override
    public void deleteBook(long id) {
    booksDao.deleteBook(id);
    }

    @Override
    public List<Books> getAllBooks() {
        return booksDao.getAllBooks();
    }

    @Override
    public Books getBook(long id) {
        return booksDao.getBook(id);
    }

    @Override
    public List<Books> getAllBooks(String bookName) {
        return booksDao.getAllBooks(bookName);
    }

    @Override
    public List<Books> getAllCartBooks(String username) {
        return booksDao.getAllCartBooks(username);
    }

}
