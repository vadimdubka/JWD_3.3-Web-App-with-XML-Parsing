package com.dubatovka.app.dao;


import com.dubatovka.app.dao.exception.DAOException;
import com.dubatovka.app.entity.PlayerProfile;

public abstract class PlayerDAO extends AbstractDBDAO {
    
    /**
     * Column names of database table 'player'.
     */
    protected static final String ID = "id";
    protected static final String FIRST_NAME = "fname";
    protected static final String MIDDLE_NAME = "mname";
    protected static final String LAST_NAME = "lname";
    protected static final String BIRTHDATE = "birthdate";
    protected static final String PASSPORT = "passport";
    protected static final String SECRET_QUESTION = "question";
    protected static final String ANSWER_MD5 = "answer_md5";
    protected static final String BALANCE = "balance";
    protected static final String STATUS = "status";
    protected static final String ADMIN_ID = "admin_id";
    protected static final String COMMENTARY = "commentary";
    
    /**
     * Additional column names of database table 'user'.
     */
    protected static final String EMAIL = "email";
    
    protected PlayerDAO() {
    }
    
    public abstract int defineIdByEmail(String email) throws DAOException;
    
    public abstract String defineEmailById(int id) throws DAOException;
    
    public abstract String defineNameByEmail(String email) throws DAOException;
    
    public abstract PlayerProfile takeProfile(int id) throws DAOException;
    
    
    /**
     * Inserts Player data into 'user' table on registration.
     */
    public abstract int insertUserPlayer(String email, String password) throws DAOException;
    
    /**
     * Inserts Player data into 'player' table on registration.
     */
    public abstract boolean insertPlayer(int id, String fName, String mName, String lName) throws DAOException;
}