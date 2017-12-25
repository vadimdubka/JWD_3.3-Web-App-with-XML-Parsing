package com.dubatovka.app.service;

import com.dubatovka.app.service.impl.XMLParseServiceImpl;

public final class ServiceFactory {
    
    private ServiceFactory() {
    }
    
    public static XMLParseService getXmlParseService() {
        return new XMLParseServiceImpl();
    }
}