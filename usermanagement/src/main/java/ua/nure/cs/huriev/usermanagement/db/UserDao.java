package ua.nure.cs.huriev.usermanagement.db;

import ua.nure.cs.huriev.usermanagement.User;

import java.util.Collection;

public interface UserDao {
    User create(User entity) throws DatabaseException;

    User find(Long id) throws DatabaseException;

    void update(User entity) throws DatabaseException;

    void delete(User entity) throws DatabaseException;

    Collection<User> findAll() throws DatabaseException;

    void setConnectionFactory(ConnectionFactory connectionFactory);
}