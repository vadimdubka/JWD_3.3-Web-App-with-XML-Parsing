package com.dubatovka.app.service.impl;

import com.dubatovka.app.dao.CandyDAO;
import com.dubatovka.app.dao.DAOFactory;
import com.dubatovka.app.dao.candybuilder.xmlvalidator.XMLValidationException;
import com.dubatovka.app.entity.candy.Candy;
import com.dubatovka.app.service.XMLParseService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class XMLParseServiceImpl implements XMLParseService {
    private static final Logger logger = LogManager.getLogger(XMLParseServiceImpl.class);
    private CandyDAO candyDAO = DAOFactory.getInstance().getCandyDAO();
    
    //TODO лучше получать упорядоченным списком
    //TODO может лучше не лезсть каждый раз в БД, если уже получил все данные.
    @Override
    public List<Candy> getCandiesFromXMLDoc(String documentPath, String schemaPath, String parserType) {
    
        List<Candy> candiesList = null;
        try {
            candiesList = candyDAO.readCandies(documentPath, schemaPath, parserType);
        } catch (XMLValidationException e) {
            logger.log(Level.ERROR, e);
        }
        
        return  candiesList;
    }
}
