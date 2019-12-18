package ua.nure.cs.huriev.usermanagement.db;

import ua.nure.cs.huriev.usermanagement.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MockUserDao implements Dao {

    private long id = 0;
    private Map users = new HashMap();

    public User create(User user) throws DatabaseException {
        Long currId = new Long(++id);
        user.setId(currId);
        this.users.put(currId.longValue(), user);
        return user;
    }

    public void update(User user) throws DatabaseException {
        Long currId = user.getId();
        users.remove(currId);
        users.put(currId.longValue(), user);
    }

    public void delete(User userId) throws DatabaseException {
        Long currId = new Long(String.valueOf(userId));
        users.remove(currId.longValue());
    }

    public User find(long userId) throws DatabaseException {
        Long currId = new Long(userId);
        return (User) users.get(currId.longValue());
    }

    public Collection findAll() throws DatabaseException {
        return users.values();
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {

    }
}