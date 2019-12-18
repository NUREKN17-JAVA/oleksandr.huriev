package ua.nure.cs.huriev.usermanagement.db;

import ua.nure.cs.huriev.usermanagement.User;

import java.util.Collection;


public interface Dao {
    /*
    T create(T entity) throws DatabaseException;

    void update(T entity) throws DatabaseException;

    void delete(T entity) throws DatabaseException;

    T find(Long id) throws DatabaseException;

    Collection<T> findAll() throws DatabaseException;

    void setConnectionFactory(ConnectionFactory connectionFactory);
     */

    User create(User entity) throws DatabaseException;

    void update(User entity) throws DatabaseException;

    User find(long userId) throws DatabaseException;

    Collection<User> findAll() throws DatabaseException;

    void setConnectionFactory(ConnectionFactory connectionFactory);

    void delete(User userId) throws DatabaseException;
}