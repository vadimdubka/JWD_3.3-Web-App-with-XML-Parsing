package com.dubatovka.app.service;

import com.dubatovka.app.service.impl.XMLParseServiceImpl;

public final class ServiceFactory {
    private static final  ServiceFactory INSTANCE = new ServiceFactory();
    
    private ServiceFactory() {
    }
    
    public static ServiceFactory getInstance() {
        return INSTANCE;
    }
    
    
    public XMLParseService getXmlParseService() {
        return new XMLParseServiceImpl();
    }
}