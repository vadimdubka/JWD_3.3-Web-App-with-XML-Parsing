package com.dubatovka.app.dao.candybuilder;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class CandyBuilderFactory {
    private static final Logger logger = LogManager.getLogger(CandyBuilderFactory.class);
    
    private enum ParserType {
        SAX, DOM, STAX
    }
    
    public static AbstractCandyBuilder createCandyBuilder(String parserType) {
        ParserType type = ParserType.valueOf(parserType.toUpperCase());
        switch (type) {
            case SAX:
                logger.log(Level.INFO, "Parsing with use of " + parserType + " parser.");
                return new CandiesSAXBuilder();
            case DOM:
                logger.log(Level.INFO, "Parsing with use of " + parserType + " parser.");
                return new CandiesDOMBuilder();
            case STAX:
                logger.log(Level.INFO, "Parsing with use of " + parserType + " parser.");
                return new CandiesStAXBuilder();
            default:
                throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }
    
    private CandyBuilderFactory() {
    }
}