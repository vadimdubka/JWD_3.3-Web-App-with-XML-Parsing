package com.dubatovka.app.controller;

public class ConfigConstant {
    
    /**
     * Parameters names from requests.
     */
    public static final String PARAM_COMMAND_TYPE = "command_type";
    public static final String PARAM_DOCUMENT_PATH = "document_path";
    public static final String PARAM_SCHEMA_PATH = "schema_path";
    public static final String PARAM_PARSER_TYPE = "parser_type";
    public static final String PARAM_PAGE_NUMBER = "page_number";
    
    /**
     * Attribute names for requests.
     */
    public static final String ATTR_CANDY_PAGE_MODEL = "candy_page_model";
    
    /**
     * Attribute names for session.
     */
    public static final String ATTR_PAGE_MODEL_BUILDER = "page_model_builder";
    /**
     * Navigation response types
     */
    public static final String FORWARD = "forward";
    public static final String REDIRECT = "redirect";
    
    /**
     * Common JSP pages paths
     */
    public static final String PAGE_INDEX = "/index.jsp";
    public static final String PAGE_CANDIES = "/candies.jsp";
    
    /**
     * Common navigation queries
     */
    public static final String GOTO_PAGINATION = "/controller?command_type=goto_pagination";
    
    private ConfigConstant() {
    }
}