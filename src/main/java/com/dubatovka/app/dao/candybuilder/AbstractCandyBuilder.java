package com.dubatovka.app.dao.candybuilder;

import com.dubatovka.app.dao.DAOFactory;
import com.dubatovka.app.dao.DocumentDAO;
import com.dubatovka.app.dao.candybuilder.xmlvalidator.XMLValidationException;
import com.dubatovka.app.entity.Candy;

import java.util.ArrayList;
import java.util.List;

//TODO почитать пр билдер паттерн
public abstract class AbstractCandyBuilder {
    protected DocumentDAO documentDAO = DAOFactory.getInstance().getDocumentDAO();
    protected List<Candy> candies;

    public AbstractCandyBuilder() {
        candies = new ArrayList<>();
    }

    public AbstractCandyBuilder(List<Candy> candies) {
        this.candies = candies;
    }

    public List<Candy> getCandies() {
        return candies;
    }

    public abstract void buildCandyList(String documentPath, String schemaPath) throws XMLValidationException;
    
    
    
}