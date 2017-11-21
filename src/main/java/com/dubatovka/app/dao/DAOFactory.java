package com.dubatovka.app.dao;

import com.dubatovka.app.dao.impl.*;

//TODO Почему бы даофектори не сделать приватный конструктор и статические методы по возврату дао объектов
public final class DAOFactory {
    private static final DAOFactory INSTANCE = new DAOFactory();
    private UserDAO userDAO;
    private PlayerDAO playerDAO;
    private JAuctionUserDAO jAuctionUserDAO;
    private DocumentDAO documentDAO;
    private CandyDAO candyDAO;
    
    private DAOFactory() {
    }
    
    public static DAOFactory getInstance() {
        return INSTANCE;
    }
    
    public UserDAO getUserDAO() {
        if (userDAO == null) {
            userDAO = new UserDAOImpl();
        }
        return userDAO;
    }
    
    public DocumentDAO getDocumentDAO() {
        if (documentDAO == null) {
            documentDAO = new DocumentDAOImpl();
        }
        return documentDAO;
    }
    
    public CandyDAO getCandyDAO() {
        if (candyDAO == null) {
            candyDAO = new CandyDAOImpl();
        }
        return candyDAO;
    }
    
    public PlayerDAO getPlayerDAO() {
        if (playerDAO == null) {
            playerDAO = new PlayerDAOImpl();
        }
        return playerDAO;
    }
    
    public JAuctionUserDAO getjAuctionUserDAO() {
        if (jAuctionUserDAO == null) {
            jAuctionUserDAO = new JAuctionUserDAOImpl();
        }
        return jAuctionUserDAO;
    }
}