package com.dubatovka.app.dao.impl;

import com.dubatovka.app.dao.CandyDAO;
import com.dubatovka.app.dao.candybuilder.AbstractCandyBuilder;
import com.dubatovka.app.dao.candybuilder.CandyBuilderFactory;
import com.dubatovka.app.entity.candy.Candy;
import com.dubatovka.app.dao.candybuilder.xmlvalidator.XMLValidationException;

import java.util.List;

public class CandyDAOImpl implements CandyDAO {
    
    @Override
    public List<Candy> readCandies(String documentPath, String schemaPath, String parserType) throws XMLValidationException {
        AbstractCandyBuilder candyBuilder = CandyBuilderFactory.createCandyBuilder(parserType);
        candyBuilder.buildCandyList(documentPath, schemaPath);
        return candyBuilder.getCandies();
    }
}
