package com.dubatovka.app.dao.impl;

import com.dubatovka.app.dao.JAuctionUserDAO;
import com.dubatovka.app.dao.exception.DAOException;
import com.dubatovka.app.entity.JAuctionUser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JAuctionUserDAOImpl extends JAuctionUserDAO {
    
    /**
     * Checks if user with definite e-mail exists by selecting it.
     */
    private static final String SQL_DEFINE_EMAIL_EXISTS = "SELECT email " +
            "FROM user " +
            "WHERE email=?";
    
    /**
     * Select user with definite e-mail and password encrypted by MD5 encryptor.
     */
    private static final String SQL_AUTH = "SELECT id, email, role, registration_date " +
            "FROM user " +
            "WHERE email=? AND password_md5=?";
    
    /**
     * Select user by its id.
     */
    private static final String SQL_SELECT_BY_ID = "SELECT id, email, role, registration_date " +
            "FROM user " +
            "WHERE id=?";
    /**
     * Select users due to given patterns.
     */
    private static final String SQL_SELECT_USERS = "SELECT id, email, role, registration_date " +
            "FROM user " +
            "WHERE id LIKE ?";
    /**
     * Select user by its e-mail.
     */
    private static final String SQL_SELECT_BY_EMAIL = "SELECT id, email, role, registration_date " +
            "FROM user " +
            "WHERE email=?";
    /**
     * Select password encrypted by MD5 encryptor by its user id.
     */
    private static final String SQL_SELECT_PASSWORD = "SELECT password_md5 " +
            "FROM user " +
            "WHERE id=?";
    
    /**
     * Constructs DAO object by taking {@link WrappedConnection} object from {@link ConnectionPool} collection.
     *
     * @see AbstractDAO#AbstractDAO()
     */
    public JAuctionUserDAOImpl() {
    }
    
    
    /**
     * Takes {@link JCasinoUser} by its e-mail and password encrypted by MD5 encryptor.
     *
     * @param email    user e-mail
     * @param password user password encrypted by MD5 encryptor
     * @return taken {@link JCasinoUser} or null
     * @throws DAOException if {@link SQLException} occurred while working with database
     * @see WrappedConnection#prepareStatement(String)
     * @see PreparedStatement
     * @see ResultSet
     * @see #buildUser(ResultSet)
     */
    @Override
    public JAuctionUser authorizeUser(String email, String password) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_AUTH)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return buildUser(resultSet);
        } catch (SQLException e) {
            throw new DAOException("Database connection error while authorizing user. " + e);
        }
    }
    
    
    /**
     * Checks if definite {@link JCasinoUser} password matches to given password. Passwords are encrypted by MD5
     * encryptor.
     *
     * @param id       user id
     * @param password password encrypted by MD5 encryptor to match
     * @return true if passwords match
     * @throws DAOException if {@link SQLException} occurred while working with database
     * @see WrappedConnection#prepareStatement(String)
     * @see PreparedStatement
     * @see ResultSet
     */
    @Override
    public boolean checkPassword(int id, String password) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_PASSWORD)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next() && password.equals(resultSet.getString(PASSWORD_MD5));
        } catch (SQLException e) {
            throw new DAOException("Database connection error while checking password. " + e);
        }
    }
    
    /**
     * Builds {@link JCasinoUser} object by parsing {@link ResultSet} object.
     *
     * @param resultSet {@link ResultSet} object to parse
     * @return parsed {@link JCasinoUser} object or null
     * @throws SQLException if the columnLabel is not valid; if a database access error occurs or this method is called
     *                      on a closed result set
     */
    private JAuctionUser buildUser(ResultSet resultSet) throws SQLException {
        JAuctionUser user = null;
        if (resultSet.next()) {
            user = new JAuctionUser();
            user.setId(resultSet.getInt(ID));
            user.setEmail(resultSet.getString(EMAIL));
            user.setRole(JAuctionUser.UserRole.valueOf(resultSet.getString(ROLE).toUpperCase()));
            user.setRegistrationDate(resultSet.getDate(REGISTRATION_DATE).toLocalDate());
        }
        return user;
    }
    
}