package com.dubatovka.app.dao.impl;

import com.dubatovka.app.dao.UserDAO;
import com.dubatovka.app.dao.exception.DAOException;
import com.dubatovka.app.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends UserDAO {
    
    /**
     * Selects all users.
     */
    private static final String SQL_SELECT_ALL_USERS = "SELECT * FROM user ORDER BY last_name";
    /**
     * Selects definite users by first and last name.
     */
    private static final String SQL_SELECT_USERS_BY_FIRST_AND_LAST_NAME = "SELECT * FROM user WHERE first_name=? AND last_name=?";
    
    public UserDAOImpl() {
    }
    
    @Override
    public List<User> readUsers() throws DAOException {
        List<User> userList;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS);
            userList = createUserList(resultSet);
        } catch (SQLException e) {
            throw new DAOException("Database connection error while read users. " + e);
        }
        return userList;
    }
    
    @Override
    public List<User> readUsers(String fName, String lName) throws DAOException {
        List<User> users;
        ResultSet resultSet;
        try (PreparedStatement prepStatement = connection.prepareStatement(SQL_SELECT_USERS_BY_FIRST_AND_LAST_NAME)) {
            prepStatement.setString(1, fName);
            prepStatement.setString(2, lName);
            resultSet = prepStatement.executeQuery();
            users = createUserList(resultSet);
        } catch (SQLException e) {
            throw new DAOException("Database connection error while read users. " + e);
        }
        
        return users;
    }
    
    private User createUser(ResultSet resultSet) throws SQLException {
        User user = null;
        if (resultSet.next()) {
            user = new User();
            user.setId(resultSet.getInt(ID));
            user.setfName(resultSet.getString(FIRST_NAME));
            user.setlName(resultSet.getString(LAST_NAME));
            user.setPhone(resultSet.getString(PHONE));
            user.setEmail(resultSet.getString(EMAIL));
        }
        return user;
    }
    
    private List<User> createUserList(ResultSet resultSet) throws SQLException {
        List<User> userList = new ArrayList<>();
        User user;
        do {
            user = createUser(resultSet);
            if (user != null) {
                userList.add(user);
            }
        } while (user != null);
        
        return userList;
    }
}
