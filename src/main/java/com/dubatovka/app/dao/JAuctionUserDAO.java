package com.dubatovka.app.dao;


import com.dubatovka.app.dao.exception.DAOException;
import com.dubatovka.app.entity.JAuctionUser;


public abstract class JAuctionUserDAO extends AbstractDBDAO {
    
    /**
     * Column names of database table 'user'.
     */
    protected static final String ID = "id";
    protected static final String PASSWORD_MD5 = "password_md5";
    protected static final String EMAIL = "email";
    protected static final String ROLE = "role";
    protected static final String REGISTRATION_DATE = "registration_date";
    
    
    protected JAuctionUserDAO() {
    }
    
    /**
     * Takes JAuctionUser by its e-mail and password encrypted by MD5 encryptor.
     */
    public abstract JAuctionUser authorizeUser(String email, String password) throws DAOException;
    
    /**
     * Checks if definite JAuctionUser password matches to given password. Passwords are encrypted by MD5
     * encryptor.
     */
    public abstract boolean checkPassword(int id, String password) throws DAOException;
}