package com.service;



import com.model.Books;

import java.util.List;

/**
 * Created by bohdan on 13.08.16.
 */
public interface BooksService {
    long createBook(Books book);
    Books updateBook(Books book);
    void deleteBook(long id);
    List<Books> getAllBooks();
    Books getBook(long id);
    List<Books> getAllBooks(String bookName);
    List<Books> getAllCartBooks(String username);
}
