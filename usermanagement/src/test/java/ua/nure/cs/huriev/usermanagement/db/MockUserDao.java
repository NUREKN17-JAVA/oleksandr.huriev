package ua.nure.cs.huriev.usermanagement.db;

import ua.nure.cs.huriev.usermanagement.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MockUserDao implements Dao {
    private Long id = (long) 0;
    private Map users = new HashMap();

    @ Override
    public User create(User user) throws DatabaseException {
        Long currentId = new Long(++id);
        user.setId(currentId);
        users.put(currentId, user);
        return user;
    }

    @Override
    public void update(User user) throws DatabaseException {
        Long currentId = user.getId();
        users.remove(currentId);
        users.put(currentId, user);
    }

    @Override
    public void delete(User user) throws DatabaseException {
        Long currentId = user.getId();
        users.remove(currentId);
    }

    @Override
    public User find(Long id) throws DatabaseException {
        return (User) users.get(id);
    }

    @Override
    public Collection findAll() throws DatabaseException {
        return users.values();
    }

    @Override
    public void setConnectionFactory(ConnectionFactory connectionFactory) {

    }

}