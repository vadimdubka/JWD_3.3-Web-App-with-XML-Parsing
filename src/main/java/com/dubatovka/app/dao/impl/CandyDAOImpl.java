package com.dubatovka.app.dao.impl;

import com.dubatovka.app.dao.CandyDAO;
import com.dubatovka.app.dao.candybuilder.CandyBuilderDirector;
import com.dubatovka.app.entity.Candy;

import java.util.List;

public class CandyDAOImpl implements CandyDAO {
    
    @Override
    public List<Candy> readCandies(String documentPath, String schemaPath, String parserType) {
        CandyBuilderDirector candyBuilderDirector = new CandyBuilderDirector(parserType);
        List<Candy> candyList = candyBuilderDirector.constructCandyList(documentPath, schemaPath);
        return candyList;
    }
}
