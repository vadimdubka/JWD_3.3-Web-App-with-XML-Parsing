package com.dubatovka.app.dao.candybuilder;

import com.dubatovka.app.entity.Candy;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CandyBuilderDirector {
    private static final Logger logger = LogManager.getLogger(CandyBuilderDirector.class);
    private final String parserType;
    
    public CandyBuilderDirector(String parserType) {
        this.parserType = parserType;
    }
    
    public List<Candy> constructCandyList(String documentPath, String schemaPath) {
        AbstractCandyBuilder candyBuilder = createCandyBuilder(parserType);
        candyBuilder.buildCandyList(documentPath, schemaPath);
        return candyBuilder.getCandies();
    }
    
    private AbstractCandyBuilder createCandyBuilder(String parserType) {
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
    
    private enum ParserType {
        SAX, DOM, STAX
    }
    
}
