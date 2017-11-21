package com.dubatovka.app.manager;

public class ConfigConstant {
    /**
     * Locale strings
     */
    public static final String DEFAULT_LOCALE = "default";
    public static final String LOCALE_RU = "ru_RU";
    public static final String LOCALE_EN = "en_US";
    
    /**
     * Additional string constants
     */
    public static final char QUERY_START_SEPARATOR = '?';
    public static final char VALUE_SEPARATOR = '=';
    public static final char PARAMETER_SEPARATOR = '&';
    public static final char MESSAGE_SEPARATOR = ' ';
    public static final String PERCENT = "%";
    public static final String EMPTY_STRING = "";
    public static final String WHITESPACE = " ";
    public static final String DOT = ".";
    public static final String ALL = "all";
    public static final String RANGE_SPLITERATOR = "\\D+";
    
    /**
     * Parameters names from requests.
     */
    public static final String PARAM_COMMAND_TYPE = "command_type";
    
    public static final String PARAM_NAME = "name";
    public static final String PARAM_SURNAME = "surname";
    
    public static final String PARAM_DOCUMENT_PATH = "document_path";
    public static final String PARAM_SCHEMA_PATH = "schema_path";
    public static final String PARAM_PARSER_TYPE = "parser_type";
    public static final String PARAM_PAGE_NUMBER = "page_number";
    
    /**
     * Attribute names for requests.
     */
    //TODO переименовать без нижнего подчеркивания
    public static final String ATTR_USERS = "users";
    public static final String ATTR_CANDY_LIST = "candy_list";
    public static final String ATTR_CANDY_PAGE_MODEL = "candy_page_model";
    
    /**
     * Navigation previous query constant
     */
    public static final String PREV_QUERY = "prevQuery";
    
    /**
     * Attribute names for session.
     */
    public static final String ATTR_PAGE_MODEL_BUILDER = "page_model_builder";
    
    public static final String ATTR_ROLE = "role";
    public static final String ATTR_USER = "user";
    public static final String ATTR_PLAYER = "player";
    public static final String ATTR_ADMIN = "admin";
    public static final String ATTR_LOCALE = "locale";
    public static final String ATTR_PREV_QUERY = "prevQuery";
    public static final String ATTR_ERROR_MESSAGE = "errorMessage";
    
    /**
     * {@link javax.servlet.http.HttpServletRequest} and {@link javax.servlet.http.HttpSession} attribute local names
     */
    public static final String ATTR_AMOUNT_INPUT = "amount_input";
    public static final String ATTR_ANSWER_INPUT = "answer_input";
    public static final String ATTR_BIRTHDATE_INPUT = "birthdate_input";
    public static final String ATTR_COMMENTARY_INPUT = "commentary_input";
    public static final String ATTR_CURRENT_STREAK = "current_streak";
    public static final String ATTR_DEMO_PLAY = "demo_play";
    public static final String ATTR_EMAIL_INPUT = "email_input";
    public static final String ATTR_FNAME_INPUT = "fname_input";
    public static final String ATTR_LNAME_INPUT = "lname_input";
    public static final String ATTR_MNAME_INPUT = "mname_input";
    
    /**
     * {@link javax.servlet.http.HttpServletRequest} parameter names
     */
    public static final String PARAM_AMOUNT = "amount";
    public static final String PARAM_ANSWER = "answer";
    public static final String PARAM_BALANCE = "balance";
    public static final String PARAM_BET = "bet";
    public static final String PARAM_BIRTHDATE = "birthdate";
    public static final String PARAM_COMMAND = "command";
    public static final String PARAM_COMMENTARY = "commentary";
    public static final String PARAM_DEMO = "demo";
    public static final String PARAM_DISABLE_RANGES = "disable_ranges";
    public static final String PARAM_EMAIL = "email";
    public static final String PARAM_EMAIL_CODE = "email_code";
    public static final String PARAM_FILTER_NOT_PAID = "filter_not_paid";
    public static final String PARAM_FILTER_OVERDUED = "filter_overdued";
    public static final String PARAM_FNAME = "fname";
    public static final String PARAM_HEADER = "header";
    public static final String PARAM_ID = "id";
    public static final String PARAM_LINE1 = "line1";
    public static final String PARAM_LINE2 = "line2";
    public static final String PARAM_LINE3 = "line3";
    public static final String PARAM_LINE4 = "line4";
    public static final String PARAM_LINE5 = "line5";
    public static final String PARAM_LNAME = "lname";
    public static final String PARAM_LOCALE = "locale";
    public static final String PARAM_MNAME = "mname";
    public static final String PARAM_PASSWORD = "password";
    public static final String PARAM_PASSWORD_AGAIN = "password_again";
    
    /**
     * Server message property keys
     */
    public static final String MESSAGE_INVALID_EMAIL = "invalid.email";
    public static final String MESSAGE_INVALID_NAME = "invalid.name";
    public static final String MESSAGE_INVALID_PASSWORD = "invalid.password";
    public static final String MESSAGE_LOGIN_MISMATCH = "login.mismatch";
    public static final String MESSAGE_PASSWORD_MISMATCH = "password.mismatch";
    
    /**
     * Navigation response types
     */
    public static final String FORWARD = "forward";
    public static final String REDIRECT = "redirect";
    
    /**
     * Common JSP pages paths
     */
    public static final String PAGE_INDEX = "/index.jsp";
    public static final String PAGE_USERS = "/pages/users.jsp";
    public static final String PAGE_CANDIES = "/candies.jsp";
    public static final String PAGE_REGISTER = "/register.jsp";
    public static final String PAGE_MAIN = "/pages/main.jsp";
    public static final String PAGE_ERROR_500 = "/pages/error/error_500.jsp";
    
    /**
     * Common navigation queries
     */
    public static final String GOTO_INDEX = "/controller?command_type=goto_index";
    public static final String GOTO_PAGINATION = "/controller?command_type=goto_pagination";
    public static final String GOTO_REGISTER = "/controller?command_type=goto_register";
    public static final String GOTO_MAIN = "/controller?command_type=goto_main";
    public static final String GOTO_ERROR_500 = "/controller?command_type=goto_error_500";
    public static final String BACK_FROM_ERROR = "/controller?command_type=back_from_error";
    
    private ConfigConstant() {
    }
}