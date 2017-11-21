package com.dubatovka.app.dao.candybuilder.xmlvalidator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

class ValidatorSAXHandler extends DefaultHandler {
    //TODO Посмотреть, везде ли нам нужен логер с private static final
    private static final Logger logger = LogManager.getLogger(ValidatorSAXHandler.class);
    
    ValidatorSAXHandler() {
    }
    
    public void warning(SAXParseException e) {
        logger.log(Level.WARN, e.getMessage());
    }
    
    public void error(SAXParseException e) {
        logger.log(Level.ERROR, e.getMessage());
    }
    
    public void fatalError(SAXParseException e) {
        logger.log(Level.FATAL, e.getMessage());
    }
    
}
