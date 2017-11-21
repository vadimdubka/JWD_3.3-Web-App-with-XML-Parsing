package com.dubatovka.app.service;

import com.dubatovka.app.service.impl.XMLParseServiceImpl;
import com.dubatovka.app.service.impl.UserServiceImpl;

public final class ServiceFactory {
    private static final ServiceFactory INSTANCE = new ServiceFactory();
    
    private UserService userService;
    private XMLParseService xmlParseService;
    
    private ServiceFactory() {
    }
    
    public static ServiceFactory getInstance() {
        return INSTANCE;
    }
    
    public UserService getUserService() {
        if (userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }
    
    public XMLParseService getXmlParseService() {
        if (xmlParseService == null) {
            xmlParseService = new XMLParseServiceImpl();
        }
        return xmlParseService;
    }
}