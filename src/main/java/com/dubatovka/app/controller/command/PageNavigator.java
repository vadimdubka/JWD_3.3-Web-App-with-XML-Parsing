package com.dubatovka.app.controller.command;

import static com.dubatovka.app.manager.ConfigConstant.*;

public enum PageNavigator {
    
    FORWARD_GOTO_PAGINATION (GOTO_PAGINATION, FORWARD),
    FORWARD_PAGE_CANDIES(PAGE_CANDIES, FORWARD),
    REDIRECT_PAGE_INDEX(PAGE_INDEX, REDIRECT),
    FORWARD_PAGE_INDEX(PAGE_INDEX, FORWARD);
    private String query;
    private String responseType;
    
    PageNavigator(String query, String responseType) {
        this.query = query;
        this.responseType = responseType;
    }
    
    public String getQuery() {
        return query;
    }
    
    public String getResponseType() {
        return responseType;
    }
}