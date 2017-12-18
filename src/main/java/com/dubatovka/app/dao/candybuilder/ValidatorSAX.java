package com.dubatovka.app.dao.candybuilder;

import com.dubatovka.app.dao.DAOFactory;
import com.dubatovka.app.dao.DocumentDAO;
import com.dubatovka.app.dao.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public final class ValidatorSAX {
    private static final Logger logger = LogManager.getLogger(ValidatorSAX.class);
    
    private static DocumentDAO documentDAO = DAOFactory.getInstance().getDocumentDAO();
    private static String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
    private static SchemaFactory factory = SchemaFactory.newInstance(language);
    
    private ValidatorSAX() {
    }
    
    public static boolean isDocumentValid(String documentName, String schemaName) {
        boolean isValid = false;
        
        try {
            File documentFile = documentDAO.getFile(documentName);
            File schemaFile = documentDAO.getFile(schemaName);
            
            Schema schema = factory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(documentFile);
            validator.validate(source);
            isValid = true;
            logger.log(Level.INFO, documentName + " is valid");
        } catch (DAOException | SAXException | IOException e) {
            logger.log(Level.ERROR, "Document is not valid because " + e.getMessage());
        }
        return isValid;
    }
}
