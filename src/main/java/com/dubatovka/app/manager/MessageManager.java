package com.dubatovka.app.manager;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import static com.dubatovka.app.manager.ConfigConstant.*;


public class MessageManager {
    private static final Logger LOGGER = LogManager.getLogger(MessageManager.class);
    
    /**
     * Path to bundle 'messages'.
     */
    private static final String PATH = "prop/messages";
    /**
     * The class instance with en_US locale.
     */
    private static final MessageManager messageManagerUS = new MessageManager(new Locale("en", "US"));
    /**
     * The class instance with ru_RU locale.
     */
    private static final MessageManager messageManagerRU = new MessageManager(new Locale("ru", "RU"));
    /**
     * The class instance with default locale.
     */
    private static final MessageManager messageManagerDefault = messageManagerRU;
    
    private ResourceBundle bundle;
    
    private MessageManager(Locale locale) {
        bundle = ResourceBundle.getBundle(PATH, locale);
    }
    
    
    public static MessageManager getMessageManager(String locale) {
        switch (locale) {
            case LOCALE_EN:
                return messageManagerUS;
            case LOCALE_RU:
                return messageManagerRU;
            default:
                return messageManagerDefault;
        }
    }
    
    public Locale getLocale() {
        return bundle.getLocale();
    }
    
    public String getMessage(String key) {
        String property;
        try {
            property = bundle.getString(key);
        } catch (MissingResourceException e) {
            LOGGER.log(Level.ERROR, e);
            property = EMPTY_STRING;
        }
        return property;
    }
    
    
}
