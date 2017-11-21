package com.dubatovka.app.dao.candybuilder;

import com.dubatovka.app.dao.DAOFactory;
import com.dubatovka.app.dao.DocumentDAO;
import com.dubatovka.app.dao.candybuilder.xmlvalidator.XMLValidationException;
import com.dubatovka.app.entity.Candy;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCandyBuilder {
    DocumentDAO documentDAO = DAOFactory.getInstance().getDocumentDAO();
    List<Candy> candies;
    
    AbstractCandyBuilder() {
        candies = new ArrayList<>();
    }
    
    public List<Candy> getCandies() {
        return candies;
    }
    
    public abstract void buildCandyList(String documentPath, String schemaPath) throws XMLValidationException;
}