package com.dubatovka.app.dao;

import com.dubatovka.app.dao.exception.DAOException;
import com.dubatovka.app.entity.User;

import java.util.List;

public abstract class UserDAO extends AbstractDBDAO {
    /**
     * Column names of database table 'news'.
     */
    protected static final String ID = "id";
    protected static final String FIRST_NAME = "first_name";
    protected static final String LAST_NAME = "last_name";
    protected static final String PHONE = "phone";
    protected static final String EMAIL = "email";
    
    protected UserDAO() {
    }
    
    public abstract List<User> readUsers() throws DAOException;
    
    public abstract List<User> readUsers(String fName, String lName) throws DAOException;
}
