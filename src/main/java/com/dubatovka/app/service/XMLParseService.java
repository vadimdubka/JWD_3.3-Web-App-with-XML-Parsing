package com.dubatovka.app.service;

import com.dubatovka.app.entity.candy.Candy;

import java.util.List;

public interface XMLParseService {
        
    List<Candy> getCandiesFromXMLDoc(String documentPath, String schemaPath, String parserType);
}
