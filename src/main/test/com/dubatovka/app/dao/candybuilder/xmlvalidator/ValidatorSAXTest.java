package com.dubatovka.app.dao.candybuilder.xmlvalidator;

import com.dubatovka.app.dao.candybuilder.ValidatorSAX;
import org.junit.Assert;
import org.junit.Test;

public class ValidatorSAXTest {
    private static final String TEST_XML_PATH = "data/candies_test.xml";
    private static final String TEST_XSD_PATH = "data/candies_test.xsd";
    private static final String TEST_INVALID_XML_PATH = "data/candies_invalid_test.xml";
    
    @Test
    public void validateCheck()  {
        boolean actual = ValidatorSAX.isDocumentValid(TEST_XML_PATH, TEST_XSD_PATH);
        Assert.assertTrue(String.format("%s is invalid.", TEST_XML_PATH), actual);
    }
    
    @Test
    public void validateExceptionCheck() {
        boolean actual = ValidatorSAX.isDocumentValid(TEST_INVALID_XML_PATH, TEST_XSD_PATH);
        Assert.assertFalse(String.format("%s is valid.", TEST_XML_PATH), actual);
    }
}