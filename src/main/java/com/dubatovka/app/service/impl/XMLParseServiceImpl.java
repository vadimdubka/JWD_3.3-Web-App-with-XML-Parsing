package com.dubatovka.app.service.impl;

import com.dubatovka.app.dao.CandyDAO;
import com.dubatovka.app.dao.DAOFactory;
import com.dubatovka.app.entity.Candy;
import com.dubatovka.app.service.XMLParseService;

import java.util.List;

public class XMLParseServiceImpl implements XMLParseService {
    private CandyDAO candyDAO = DAOFactory.getInstance().getCandyDAO();
    
    @Override
    public List<Candy> getCandiesFromXMLDoc(String documentPath, String schemaPath, String parserType) {
        List<Candy> candiesList = candyDAO.readCandies(documentPath, schemaPath, parserType);
        return candiesList;
    }
}
