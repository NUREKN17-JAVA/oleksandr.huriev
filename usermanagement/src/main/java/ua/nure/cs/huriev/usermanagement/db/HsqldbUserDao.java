package ua.nure.cs.huriev.usermanagement.db;

import ua.nure.cs.huriev.usermanagement.User;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;



class HsqldbUserDao implements Dao<User> {

    private static final String SELECT_FIND_ALL = "SELECT id, firstname, lastname, dateofbirth FROM users";
    private static final String INSERT_QUERY = "INSERT INTO users (firstname, lastname, dateofbirth) VALUES (?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE users SET firstname = ?, lastname = ?, dateofbirth = ?  WHERE id = ?";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM users WHERE id = ?";
    private ConnectionFactory connectionFactory;

    public HsqldbUserDao() {
    }

    public HsqldbUserDao(ConnectionFactory connectoinFactory) {
        this.connectionFactory = connectoinFactory;
    }

    public ConnectionFactory getConnectoinFactory() {
        return connectionFactory;
    }

    public void setConnectionFactory(ConnectionFactory connectoinFactory) {
        this.connectionFactory = connectoinFactory;
    }

    public User create(User entity) throws DatabaseException {
        try {
            Connection connection = connectionFactory.createConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setDate(3, new Date(entity.getDateOfBirth().getTime()));

            int numberOfRows = statement.executeUpdate();
            if (numberOfRows != 1) {
                throw new DatabaseException("Number of rows =" + numberOfRows);
            }
            CallableStatement callableStatement = connection
                    .prepareCall("call IDENTITY()");
            ResultSet keys = callableStatement.executeQuery();
            if (keys.next()) {
                entity.setId(new Long(keys.getLong(1))); //Mutation
            }
            keys.close();
            callableStatement.close();
            statement.close();
            connection.close();
            return entity;
        } catch (DatabaseException e) {
            throw e;
        }
        catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public User find(Long id) throws DatabaseException {
        User resultUser = new User();
        try {
            Connection connection = connectionFactory.createConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            resultUser = new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4));
            statement.close();
            resultSet.close();
            connection.close();
            return resultUser;
        } catch (DatabaseException e) {
            throw e;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public void update(User entity) throws DatabaseException {
        try  {
            Connection connection = connectionFactory.createConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setDate(3, new Date(entity.getDateOfBirth().getTime()));
            statement.setLong(4, entity.getId());
            int updatedRows = statement.executeUpdate();
            if (updatedRows != 1) {
                throw new DatabaseException("Number of updated rows =" + updatedRows);
            }
            statement.close();
            connection.close();
        } catch (DatabaseException e) {
            throw e;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public void delete(User entity) throws DatabaseException {
        try  {
            Connection connection = connectionFactory.createConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
            statement.setLong(1, entity.getId());
            int updatedRows = statement.executeUpdate();
            if (updatedRows != 1) {
                throw new DatabaseException("Number of deleted rows =" + updatedRows);
            }
            statement.close();
            connection.close();
        } catch (DatabaseException e) {
            throw e;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    public Collection<User> findAll() throws DatabaseException {
        Collection<User> result = new LinkedList<User>();
        try {
            Connection connection = connectionFactory.createConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_FIND_ALL);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setDateOfBirth(resultSet.getDate(4));
                result.add(user);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (DatabaseException e) {
            throw e;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
        return result;
    }

}