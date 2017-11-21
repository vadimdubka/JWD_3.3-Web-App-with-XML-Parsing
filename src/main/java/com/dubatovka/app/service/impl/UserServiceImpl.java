package com.dubatovka.app.service.impl;

import com.dubatovka.app.service.UserService;
import com.dubatovka.app.dao.UserDAO;
import com.dubatovka.app.dao.exception.DAOException;
import com.dubatovka.app.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

//TODO сервисы делать статическими или нет? Если не будет создаваться новый объект внешнего класса (UserServiceImpl), то можно точно делать статическим.
public class UserServiceImpl extends AbstractService implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private UserDAO userDAO = daoFactory.getUserDAO();
    
    /**
     * Получить всех пользователей из базы данных.
     */
    public List<User> getAllUsers() {
        List<User> userList = null;
        try {
            userList = userDAO.readUsers();
        } catch (DAOException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        
        return userList;
    }
    
    /**
     * Получить список пользователей, удовлетворяющих полученным критериям имени и фамилии.
     */
    public List<User> getUsersByFirstAndLastName(String fName, String lName) {
        List<User> userList = null;
        try {
            userList = userDAO.readUsers(fName, lName);
        } catch (DAOException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return userList;
    }
}
