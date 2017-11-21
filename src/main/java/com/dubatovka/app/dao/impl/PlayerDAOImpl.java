package com.dubatovka.app.dao.impl;

import com.dubatovka.app.dao.PlayerDAO;
import com.dubatovka.app.dao.exception.DAOException;
import com.dubatovka.app.entity.PlayerProfile;

import java.sql.*;

public class PlayerDAOImpl extends PlayerDAO {
    
    /**
     * Additional column name used in SQL queries.
     */
    private static final String BINARY_STATUS = "bin_status";
    /**
     * Additional column name used in SQL queries.
     */
    private static final String THIS_MONTH_WITHDRAWAL = "total";
    
    /**
     * Selects player id by its e-mail.
     */
    private static final String SQL_DEFINE_ID_BY_EMAIL = "SELECT id FROM user " +
            "WHERE email=?";
    /**
     * Selects player e-mail by its id.
     */
    private static final String SQL_DEFINE_EMAIL_BY_ID = "SELECT email FROM user " +
            "WHERE id=?";
    /**
     * Selects player first name by its e-mail.
     */
    private static final String SQL_DEFINE_NAME_BY_EMAIL = "SELECT fname " +
            "FROM player NATURAL JOIN user " +
            "WHERE email=?";
    
    /**
     * Selects definite player profile data.
     */
    private static final String SQL_SELECT_PROFILE = "SELECT fname, mname, lname, birthdate, passport, question " +
            "FROM player " +
            "WHERE id=?";
    /**
     * Selects definite player profile data.
     */
    private static final String SQL_SELECT_VERIFICATION = "SELECT id, status AS bin_status, admin_id, commentary, date, passport " +
            "FROM verification " +
            "WHERE id=?";
    /**
     * Selects verification data of players who are ready to be verified by admin.
     */
    private static final String SQL_SELECT_VERIFICATION_READY = "SELECT id, status AS bin_status, admin_id, commentary, date, passport " +
            "FROM verification " +
            "WHERE status=0b011 AND passport IS NOT NULL";
    /**
     * Selects definite player account data.
     */
    private static final String SQL_SELECT_ACCOUNT = "SELECT balance, status, admin_id, commentary, bet_limit, withdrawal_limit, loan_percent, max_loan_amount, " +
            "IFNULL((SELECT ABS(SUM(amount)) FROM transaction " +
            "WHERE player.id=player_id AND amount < 0 AND MONTH(date)=MONTH(NOW()) AND YEAR(date)=YEAR(NOW())), 0) AS total " +
            "FROM player NATURAL JOIN player_status " +
            "WHERE player.id=?;";
    /**
     * Selects definite player this month withdrawal amount.
     */
    private static final String SQL_SELECT_MONTH_WITHDRAWAL = "SELECT IFNULL(ABS(SUM(amount)), 0) AS total " +
            "FROM transaction " +
            "WHERE player_id=? AND date LIKE ? AND amount < 0";
    
    /**
     * Inserts player data into 'user' table  on registration.
     */
    private static final String SQL_INSERT_USER = "INSERT INTO user (email, password_md5, role, registration_date) " +
            "VALUES (?, ?, 'player', NOW())";
    /**
     * Inserts player data into 'player' table  on registration.
     */
    private static final String SQL_INSERT_PLAYER = "INSERT INTO player (id, fname, mname, lname) " +
            "VALUES (?, ?, ?, ?)";
    /**
     * Inserts player data into 'verification' table on registration.
     */
    private static final String SQL_INSERT_VERIFICATION = "INSERT INTO verification (id) " +
            "VALUES (?)";
    
    /**
     * Updates definite player e-mail.
     */
    private static final String SQL_UPDATE_EMAIL = "UPDATE user " +
            "SET email=? " +
            "WHERE id=?";
    /**
     * Updates definite player password MD5 value.
     */
    private static final String SQL_UPDATE_PASSWORD = "UPDATE user " +
            "SET password_md5=? " +
            "WHERE id=?";
    /**
     * Updates definite player first name.
     */
    private static final String SQL_UPDATE_FNAME = "UPDATE player " +
            "SET fname=? " +
            "WHERE id=?";
    /**
     * Updates definite player middle name.
     */
    private static final String SQL_UPDATE_MNAME = "UPDATE player " +
            "SET mname=? " +
            "WHERE id=?";
    /**
     * Updates definite player last name.
     */
    private static final String SQL_UPDATE_LNAME = "UPDATE player " +
            "SET lname=? " +
            "WHERE id=?";
    /**
     * Updates definite player passport number.
     */
    private static final String SQL_UPDATE_PASSPORT_NUMBER = "UPDATE player " +
            "SET passport=? " +
            "WHERE id=?";
    /**
     * Updates definite player birthdate.
     */
    private static final String SQL_UPDATE_BIRTHDATE = "UPDATE player " +
            "SET birthdate=? " +
            "WHERE id=?";
    /**
     * Updates definite player secret question and its answer MD5 value.
     */
    private static final String SQL_UPDATE_SECRET_QUESTION = "UPDATE player " +
            "SET question=?, answer_md5=? " +
            "WHERE id=?";
    
    private static final String SQL_UPDATE_SCAN_PATH = "UPDATE verification " +
            "SET passport=? " +
            "WHERE id=?";
    /**
     * Updates definite player verification status binary value.
     */
    private static final String SQL_UPDATE_VER_STATUS_PLAYER = "UPDATE verification " +
            "SET status=? " +
            "WHERE id=?";
    /**
     * Updates definite player verification status binary value by definite admin with its comments.
     */
    private static final String SQL_UPDATE_VER_STATUS_ADMIN = "UPDATE verification " +
            "SET status=?, admin_id=?, commentary=?, date=NOW() " +
            "WHERE id=?";
    /**
     * Updates definite player balance by adding definite value to it.
     */
    private static final String SQL_UPDATE_ACCOUNT_BALANCE = "UPDATE player " +
            "SET balance=balance+? " +
            "WHERE id=?";
    /**
     * Updates definite player account status by definite admin with its comments.
     */
    private static final String SQL_UPDATE_ACCOUNT_STATUS_ADMIN = "UPDATE player " +
            "SET status=?, admin_id=?, commentary=? " +
            "WHERE id=?";
    
    
    public PlayerDAOImpl() {
    }
    
    
    @Override
    public int defineIdByEmail(String email) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DEFINE_ID_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next() ? resultSet.getInt(ID) : 0;
        } catch (SQLException e) {
            throw new DAOException("Database connection error while defining id by e-mail. " + e);
        }
    }
    
    
    @Override
    public String defineEmailById(int id) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DEFINE_EMAIL_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next() ? resultSet.getString(EMAIL) : null;
        } catch (SQLException e) {
            throw new DAOException("Database connection error while defining e-mail by id. " + e);
        }
    }
    
    
    @Override
    public String defineNameByEmail(String email) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_DEFINE_NAME_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next() ? resultSet.getString(FIRST_NAME) : null;
        } catch (SQLException e) {
            throw new DAOException("Database connection error while checking is e-mail exists. " + e);
        }
    }
    
    
    @Override
    public PlayerProfile takeProfile(int id) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_PROFILE)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            return buildPlayerProfile(resultSet);
        } catch (SQLException e) {
            throw new DAOException("Database connection error while taking player profile. " + e);
        }
    }
    
    
    @Override
    public int insertUserPlayer(String email, String password) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, email);
            statement.setString(2, password);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            return resultSet.next() ? resultSet.getInt(1) : 0;
        } catch (SQLException e) {
            throw new DAOException("Database connection error while inserting user player. " + e);
        }
    }
    
    
    @Override
    public boolean insertPlayer(int id, String fName, String mName, String lName) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_INSERT_PLAYER)) {
            statement.setInt(1, id);
            statement.setString(2, fName);
            statement.setString(3, mName);
            statement.setString(4, lName);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DAOException("Database connection error while inserting player. " + e);
        }
    }
    
    
    private PlayerProfile buildPlayerProfile(ResultSet resultSet) throws SQLException {
        PlayerProfile profile = null;
        if (resultSet.next()) {
            profile = new PlayerProfile();
            profile.setfName(resultSet.getString(FIRST_NAME));
            profile.setmName(resultSet.getString(MIDDLE_NAME));
            profile.setlName(resultSet.getString(LAST_NAME));
        }
        return profile;
    }
}