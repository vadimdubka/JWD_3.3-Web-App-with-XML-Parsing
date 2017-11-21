package com.dubatovka.app.dao.candybuilder;

public class CandyBuilderFactory {

    private enum ParserType {
        SAX, DOM, STAX
    }

    public static AbstractCandyBuilder createCandyBuilder(String parserType) {
        ParserType type = ParserType.valueOf(parserType.toUpperCase());
        switch (type) {
            case SAX:
                return new CandiesSAXBuilder();
            case DOM:
                return new CandiesDOMBuilder();
            case STAX:
                return new CandiesStAXBuilder();
            default:
                throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }
    
    private CandyBuilderFactory() {
    }
}