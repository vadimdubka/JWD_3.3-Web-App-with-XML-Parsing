package com.dubatovka.app.dao;

import com.dubatovka.app.dao.candybuilder.xmlvalidator.XMLValidationException;
import com.dubatovka.app.entity.Candy;

import java.util.List;

public interface CandyDAO {
    List<Candy> readCandies(String documentPath, String schemaPath, String parserType) throws XMLValidationException;
}
