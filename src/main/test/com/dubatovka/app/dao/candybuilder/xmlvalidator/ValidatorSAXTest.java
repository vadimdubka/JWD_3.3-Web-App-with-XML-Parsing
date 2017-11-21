package com.dubatovka.app.dao.candybuilder.xmlvalidator;

import org.junit.Assert;
import org.junit.Test;

public class ValidatorSAXTest {
    private static final String TEST_XML_PATH = "data/candies_test.xml";
    private static final String TEST_XSD_PATH = "data/candies_test.xsd";
    private static final String TEST_INVALID_XML_PATH = "data/candies_invalid_test.xml";

    @Test
    public void validateCheck() throws XMLValidationException {
        boolean actual = ValidatorSAX.validate(TEST_XML_PATH, TEST_XSD_PATH);
        Assert.assertTrue(String.format("%s is invalid.", TEST_XML_PATH), actual);
    }

    @Test(expected = XMLValidationException.class)
    public void validateExceptionCheck() throws XMLValidationException {
        ValidatorSAX.validate(TEST_INVALID_XML_PATH, TEST_XSD_PATH);
        Assert.fail(String.format("XMLValidationException should be thrown because %s is invalid", TEST_INVALID_XML_PATH));
    }
}