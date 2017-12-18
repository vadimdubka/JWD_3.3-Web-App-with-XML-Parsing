package com.dubatovka.app.dao.impl;

import com.dubatovka.app.dao.CandyDAO;
import com.dubatovka.app.dao.candybuilder.AbstractCandyBuilder;
import com.dubatovka.app.dao.candybuilder.CandyBuilderFactory;
import com.dubatovka.app.entity.Candy;

import java.util.List;

public class CandyDAOImpl implements CandyDAO {
    
    @Override
    public List<Candy> readCandies(String documentPath, String schemaPath, String parserType) {
        AbstractCandyBuilder candyBuilder = CandyBuilderFactory.createCandyBuilder(parserType);
        candyBuilder.buildCandyList(documentPath, schemaPath);
        return candyBuilder.getCandies();
    }
}
