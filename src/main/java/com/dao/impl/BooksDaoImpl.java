package com.dao.impl;


import com.dao.BooksDao;
import com.model.Books;
import com.util.SessionClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bohdan on 13.08.16.
 */
@Repository
public class BooksDaoImpl implements BooksDao {

    public BooksDaoImpl() {
    }

    @Autowired
    private SessionClass sessionClass;

    @Override
    public long createBook(Books book) {
        return (long) sessionClass.create(book);
    }

    @Override
    public Books updateBook(Books book) {
        return sessionClass.update(book);
    }

    @Override
    public void deleteBook(long id) {
        Books book = new Books();
        book.setId(id);
        sessionClass.delete(book);
    }

    @Override
    public List<Books> getAllBooks() {
        return sessionClass.fetchAll(Books.class);
    }

    @Override
    public Books getBook(long id) {
        return sessionClass.fetchById(id, Books.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Books> getAllBooks(String bookName) {
        String query = "SELECT* FROM Books WHERE name like '%" + bookName + "%'";
        List<Object[]> bookObjects = sessionClass.fetchAll(query);
        List<Books> books = new ArrayList<>();
        for (Object[] bookObject : bookObjects) {
            Books book = new Books();
            int id = (int) bookObject[0];
            String name = (String) bookObject[1];
            String author = (String) bookObject[2];
            String type = (String) bookObject[3];
            Double pric = (Double) bookObject[4];
            byte[] img = (byte[]) bookObject[5];

            book.setId(id);
            book.setName(name);
            book.setAuthor(author);
            book.setType(type);
            book.setPrice(pric);
            book.setImg(img);
            books.add(book);
        }
        System.out.println(books);
        return books;
    }

    @Override
    public List<Books> getAllCartBooks(String username) {
        String query = "SELECT* FROM UserBook  WHERE username like '" + username + "'";
        List<Object[]> bookObjects = sessionClass.fetchAll(query);
        List<Integer> integer = new ArrayList<>();
        for (Object[] bookObject : bookObjects) {
            int i = (Integer) bookObject[0];
            integer.add(i);
        }
        List<Books> list = sessionClass.fetchAll(Books.class);
        List<Books> listbooks = new ArrayList<>();
        for (Integer i: integer)
        {
            for(Books b: list)
            {
                if(b.getId()==i)
                    listbooks.add(b);
            }
        }
        return listbooks;
    }
}