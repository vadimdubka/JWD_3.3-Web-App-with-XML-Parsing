package com.dubatovka.app.dao.candybuilder;

import com.dubatovka.app.entity.Candy;
import com.dubatovka.app.entity.CandyConstants;
import com.dubatovka.app.entity.Chocolate;
import com.dubatovka.app.entity.ImportCandy;
import com.dubatovka.app.entity.Ingredients;
import com.dubatovka.app.entity.ProducedCandy;
import com.dubatovka.app.entity.Value;
import com.dubatovka.app.dao.candybuilder.xmlvalidator.XMLValidationException;
import com.dubatovka.app.dao.candybuilder.xmlvalidator.ValidatorSAX;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class CandiesDOMBuilder extends AbstractCandyBuilder {
    private static final Logger logger = LogManager.getLogger(CandiesDOMBuilder.class);
    
    private DocumentBuilder docBuilder;
    
    CandiesDOMBuilder() {
        createDocBuilder();
    }
    
    private void createDocBuilder() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.log(Level.FATAL, "ParserConfigurationException: " + e);
        }
    }
    
    @Override
    public void buildCandyList(String documentPath, String schemaPath) throws XMLValidationException {
        ValidatorSAX.validate(documentPath, schemaPath);
        
        List<Candy> candyList = new ArrayList<>();
        try {
            Document doc = docBuilder.parse(documentDAO.getInputSource(documentPath));
            Element root = doc.getDocumentElement();
            NodeList producedCandyList = root.getElementsByTagName(CandyEnum.PRODUCED_CANDY.getValue());
            for (int i = 0; i < producedCandyList.getLength(); i++) {
                Element producedCandyElement = (Element) producedCandyList.item(i);
                ProducedCandy producedCandy = buildProducedCandy(producedCandyElement);
                candyList.add(producedCandy);
            }
            NodeList importCandyList = root.getElementsByTagName(CandyEnum.IMPORT_CANDY.getValue());
            for (int i = 0; i < importCandyList.getLength(); i++) {
                Element importCandyElement = (Element) importCandyList.item(i);
                ImportCandy importCandy = buildImportCandy(importCandyElement);
                candyList.add(importCandy);
            }
            candies = candyList;
        } catch (SAXException e) {
            logger.log(Level.ERROR, "SAXException in buildCandyList method of DOMBuilder: " + e);
        } catch (IOException e) {
            logger.log(Level.ERROR, "IOException in buildCandyList method of DOMBuilder: " + e);
        }
    }
    
    private ProducedCandy buildProducedCandy(Element producedCandyElement) {
        ProducedCandy candy = new ProducedCandy();
        String producer = producedCandyElement.getAttribute(CandyEnum.PRODUCER.getValue());
        if (CandyConstants.EMPTY_STRING.equals(producer) || producer == null) {
            producer = CandyConstants.DEFAULT_PRODUCER;
        }
        candy.setProducer(producer);
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern(CandyConstants.DATE_FORMAT_PATTERN);
        try {
            Date shelfLife = format.parse(producedCandyElement.getAttribute(CandyEnum.SHELF_LIFE.getValue()));
            candy.setShelfLife(shelfLife);
        } catch (ParseException e) {
            logger.log(Level.ERROR, "ParseException while parsing SHELF_LIFE in buildProducedCandy method of DOMBuilder: " + e);
        }
        buildCandy(producedCandyElement, candy);
        return candy;
    }
    
    private ImportCandy buildImportCandy(Element importCandyElement) {
        ImportCandy candy = new ImportCandy();
        candy.setDelivery(importCandyElement.getAttribute(CandyEnum.DELIVERY.getValue()));
        candy.setCountryCode(getElementTextContent(importCandyElement, CandyEnum.COUNTRY_CODE.getValue()));
        buildCandy(importCandyElement, candy);
        return candy;
    }
    
    private void buildCandy(Element candyElement, Candy candy) {
        candy.setName(getElementTextContent(candyElement, CandyEnum.NAME.getValue()));
        candy.setType(getElementTextContent(candyElement, CandyEnum.TYPE.getValue()));
        candy.setFilled(Boolean.valueOf(getElementTextContent(candyElement, CandyEnum.FILLED.getValue())));
        candy.setEnergy(Integer.parseInt(getElementTextContent(candyElement, CandyEnum.ENERGY.getValue())));
        
        Ingredients ingredients = candy.getIngredients();
        Element ingredientsElement = (Element) candyElement.getElementsByTagName(CandyEnum.INGREDIENTS.getValue()).item(0);
        ingredients.setWater(Double.parseDouble(getElementTextContent(ingredientsElement, CandyEnum.WATER.getValue())));
        ingredients.setSugar(Double.parseDouble(getElementTextContent(ingredientsElement, CandyEnum.SUGAR.getValue())));
        ingredients.setMilk(Double.parseDouble(getElementTextContent(ingredientsElement, CandyEnum.MILK.getValue())));
        ingredients.setFructose(Double.parseDouble(getElementTextContent(ingredientsElement, CandyEnum.FRUCTOSE.getValue())));
        ingredients.setVanillin(Double.parseDouble(getElementTextContent(ingredientsElement, CandyEnum.VANILLIN.getValue())));
        ingredients.setNuts(Double.parseDouble(getElementTextContent(ingredientsElement, CandyEnum.NUTS.getValue())));
        
        Chocolate chocolate = ingredients.getChocolate();
        Element chocolateElement = (Element) ingredientsElement.getElementsByTagName(CandyEnum.CHOCOLATE.getValue()).item(0);
        chocolate.setChocolateType(getElementTextContent(chocolateElement, CandyEnum.CHOCOLATE_TYPE.getValue()));
        chocolate.setPorous(Boolean.valueOf(getElementTextContent(chocolateElement, CandyEnum.POROUS.getValue())));
        chocolate.setAmount(Double.parseDouble(getElementTextContent(chocolateElement, CandyEnum.AMOUNT.getValue())));
        
        Value value = candy.getValue();
        Element valueElement = (Element) candyElement.getElementsByTagName(CandyEnum.VALUE.getValue()).item(0);
        value.setProteins(Double.parseDouble(getElementTextContent(valueElement, CandyEnum.PROTEINS.getValue())));
        value.setFats(Double.parseDouble(getElementTextContent(valueElement, CandyEnum.FATS.getValue())));
        value.setCarbohydrates(Double.parseDouble(getElementTextContent(valueElement, CandyEnum.CARBOHYDRATES.getValue())));
    }
    
    private String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
    
}