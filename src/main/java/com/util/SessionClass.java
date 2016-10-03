package com.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import com.model.Books;
import com.model.UserRole;
import com.model.Users;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;


@Repository
public class SessionClass {

    @Autowired
    private SessionFactory sessionFactory;

    public <T> Serializable create(final T entity) {
        return sessionFactory.getCurrentSession().save(entity);
    }

    public <T> T update(final T entity) {
        sessionFactory.getCurrentSession().update(entity);
        return entity;
    }

    public <T> void delete(final T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    public <T> void delete(Serializable id, Class<T> entityClass) {
        T entity = fetchById(id, entityClass);
        delete(entity);
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> fetchAll(Class<T> entityClass) {
        return sessionFactory.getCurrentSession().createQuery(" FROM "+entityClass.getName()).list();
    }

    @SuppressWarnings("rawtypes")
    public <T> List fetchAll(String query) {
        return sessionFactory.getCurrentSession().createSQLQuery(query).list();
    }

    @SuppressWarnings("unchecked")
    public <T> T fetchById(Serializable id, Class<T> entityClass) {
        return (T)sessionFactory.getCurrentSession().get(entityClass, id);
    }
    @SuppressWarnings("unchecked")
    public Users findByUserName(String username) {

        List<Users> users = new ArrayList<Users>();

        users = sessionFactory.getCurrentSession()
                .createQuery("from Users where username=?")
                .setParameter(0, username)
                .list();

        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }

    }
    @SuppressWarnings("unchecked")
    public void addUser(String username, String password)
    {
        Users user = new Users();
        UserRole userRole = new UserRole();
        user.setUsername(username);
        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt(12)));
        user.setEnabled(true);
        userRole.setUser(user);
        userRole.setRole("ROLE_USER");
        sessionFactory.getCurrentSession().save(user);
        sessionFactory.getCurrentSession().save(userRole);
    }

    public void addBookToUser(Users user ,Books books)
    {
        books.getUsersSet().add(user);
        sessionFactory.getCurrentSession().saveOrUpdate(books);
    }
}