package com.dubatovka.app.dao;

import com.dubatovka.app.dao.impl.*;

public final class DAOFactory {
    private static final DAOFactory INSTANCE = new DAOFactory();
    private DocumentDAO documentDAO;
    private CandyDAO candyDAO;
    
    private DAOFactory() {
    }
    
    public static DAOFactory getInstance() {
        return INSTANCE;
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
}