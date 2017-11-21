package com.dubatovka.app.service;

import com.dubatovka.app.service.impl.XMLParseServiceImpl;

public final class ServiceFactory {
    private static final ServiceFactory INSTANCE = new ServiceFactory();
    
    private XMLParseService xmlParseService;
    
    private ServiceFactory() {
    }
    
    public static ServiceFactory getInstance() {
        return INSTANCE;
    }
    
    public XMLParseService getXmlParseService() {
        if (xmlParseService == null) {
            xmlParseService = new XMLParseServiceImpl();
        }
        return xmlParseService;
    }
}