package com.dubatovka.app.dao.candybuilder;

public enum CandyEnum {
    CANDIES("candy"),
    CANDY("candy"),
    INGREDIENTS("ingredients"),
    CHOCOLATE("chocolate"),
    VALUE("value"),
    PRODUCED_CANDY("produced-candy"),
    SHELF_LIFE("shelf-life"),
    PRODUCER("producer"),
    IMPORT_CANDY("import-candy"),
    DELIVERY("delivery"),
    NAME("name"),
    TYPE("type"),
    FILLED("filled"),
    ENERGY("energy"),
    WATER("water"),
    SUGAR("sugar"),
    MILK("milk"),
    FRUCTOSE("fructose"),
    VANILLIN("vanillin"),
    NUTS("nuts"),
    CHOCOLATE_TYPE("chocolate-type"),
    POROUS("porous"),
    AMOUNT("amount"),
    PROTEINS("proteins"),
    FATS("fats"),
    CARBOHYDRATES("carbohydrates"),
    COUNTRY_CODE("country-code");
    
    private String value;
    
    CandyEnum(String value) {
        this.value = value;
    }
    
    public static CandyEnum stringToEnum(String string) {
        return CandyEnum.valueOf(string.toUpperCase().replaceAll("-", "_"));
    }
    
    public String getValue() {
        return value;
    }
}