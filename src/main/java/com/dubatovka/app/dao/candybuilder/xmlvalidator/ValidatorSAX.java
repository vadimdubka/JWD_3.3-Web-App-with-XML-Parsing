package com.dubatovka.app.dao.candybuilder.xmlvalidator;

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

public class ValidatorSAX {
    private static final Logger logger = LogManager.getLogger(ValidatorSAX.class);
    
    private static DocumentDAO documentDAO = DAOFactory.getInstance().getDocumentDAO();
    private static String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
    private static SchemaFactory factory = SchemaFactory.newInstance(language);
    
    public static boolean validate(String documentName, String schemaName) throws XMLValidationException {
        
        File documentFile;
        File schemaFile;
        try {
            documentFile = documentDAO.getFile(documentName);
            schemaFile = documentDAO.getFile(schemaName);
        } catch (DAOException e) {
            throw new XMLValidationException("Document is not valid because " + e.getMessage());
        }
    
        try {
            Schema schema = factory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(documentFile);
            validator.validate(source);
            logger.log(Level.INFO, documentName + " is valid");
            return true;
        } catch (SAXException e) {
            logger.log(Level.ERROR, documentName + " SAX error: " + e.getMessage());
            throw new XMLValidationException(documentName + " is not valid because " + e.getMessage());
        } catch (IOException e) {
            logger.log(Level.ERROR, "I/O error: " + e.getMessage());
        }
        
        return false;
    }
}
