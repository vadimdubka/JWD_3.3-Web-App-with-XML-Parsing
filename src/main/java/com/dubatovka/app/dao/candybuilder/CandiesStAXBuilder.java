package com.dubatovka.app.dao.candybuilder;

import com.dubatovka.app.dao.exception.DAOException;
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

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CandiesStAXBuilder extends AbstractCandyBuilder {
    private static final Logger logger = LogManager.getLogger(CandiesStAXBuilder.class);
    private XMLInputFactory inputFactory;
    
    public CandiesStAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }
    
    public CandiesStAXBuilder(List<Candy> candies) {
        super(candies);
        inputFactory = XMLInputFactory.newInstance();
    }
    
    @Override
    public void buildCandyList(String documentPath, String schemaPath) throws XMLValidationException {
        ValidatorSAX.validate(documentPath, schemaPath);
        List<Candy> candySet = new ArrayList<>();
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String name;
        
        //TODO как то оптимизировать
        try {
            File document = documentDAO.getFile(documentPath);
            inputStream = new FileInputStream(document);
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    CandyEnum candyEnum = CandyEnum.stringToEnum(name);
                    if (candyEnum == CandyEnum.PRODUCED_CANDY) {
                        Candy candy = buildProducedCandy(reader);
                        candySet.add(candy);
                    }
                    if (candyEnum == CandyEnum.IMPORT_CANDY) {
                        Candy candy = buildImportCandy(reader);
                        candySet.add(candy);
                    }
                }
            }
            candies = candySet;
        } catch (FileNotFoundException e) {
            logger.log(Level.ERROR, "File '" + documentPath + "' not found! FileNotFoundException: " + e);
        } catch (XMLStreamException e) {
            logger.log(Level.ERROR, "StAX parsing error! XMLStreamException: " + e);
        } catch (DAOException e) {
            logger.log(Level.ERROR, e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                logger.log(Level.ERROR, "Impossible to close file " + documentPath + " : " + e);
            } catch (XMLStreamException e) {
                logger.log(Level.ERROR, "Impossible to close XMLStreamReader reader: " + e);
            }
        }
    }
    
    private ProducedCandy buildProducedCandy(XMLStreamReader reader) throws XMLStreamException {
        ProducedCandy candy = new ProducedCandy();
        String producer = reader.getAttributeValue(null, CandyEnum.PRODUCER.getValue());
        if (producer == null || CandyConstants.EMPTY_STRING.equals(producer)) {
            producer = CandyConstants.DEFAULT_PRODUCER;
        }
        candy.setProducer(producer);
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern(CandyConstants.DATE_FORMAT_PATTERN);
        try {
            Date shelfLife = format.parse(reader.getAttributeValue(null, CandyEnum.SHELF_LIFE.getValue()));
            candy.setShelfLife(shelfLife);
        } catch (ParseException e) {
            logger.log(Level.ERROR, "ParseException while parsing SHELF_LIFE in buildProducedCandy method of StAXBuilder: " + e);
        }
        buildCandy(reader, candy);
        return candy;
    }
    
    private ImportCandy buildImportCandy(XMLStreamReader reader) throws XMLStreamException {
        ImportCandy candy = new ImportCandy();
        candy.setDelivery(reader.getAttributeValue(null, CandyEnum.DELIVERY.getValue()));
        buildCandy(reader, candy);
        return candy;
    }
    
    private void buildCandy(XMLStreamReader reader, Candy candy) throws XMLStreamException {
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (CandyEnum.stringToEnum(name)) {
                        case NAME:
                            candy.setName(getXMLText(reader));
                            break;
                        case TYPE:
                            candy.setType(getXMLText(reader));
                            break;
                        case FILLED:
                            candy.setFilled(Boolean.valueOf(getXMLText(reader)));
                            break;
                        case ENERGY:
                            candy.setEnergy(Integer.parseInt(getXMLText(reader)));
                            break;
                        case INGREDIENTS:
                            candy.setIngredients(buildIngredients(reader));
                            break;
                        case VALUE:
                            candy.setValue(buildValue(reader));
                            break;
                        case COUNTRY_CODE:
                            ((ImportCandy) candy).setCountryCode(getXMLText(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    CandyEnum candyEnum = CandyEnum.stringToEnum(name);
                    if (candyEnum == CandyEnum.PRODUCED_CANDY || candyEnum == CandyEnum.IMPORT_CANDY) {
                        return;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag produced- or import-candy");
    }
    
    private Ingredients buildIngredients(XMLStreamReader reader) throws XMLStreamException {
        Ingredients ingredients = new Ingredients();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (CandyEnum.stringToEnum(name)) {
                        case WATER:
                            ingredients.setWater(Double.parseDouble(getXMLText(reader)));
                            break;
                        case SUGAR:
                            ingredients.setSugar(Double.parseDouble(getXMLText(reader)));
                            break;
                        case MILK:
                            ingredients.setMilk(Double.parseDouble(getXMLText(reader)));
                            break;
                        case FRUCTOSE:
                            ingredients.setFructose(Double.parseDouble(getXMLText(reader)));
                            break;
                        case VANILLIN:
                            ingredients.setVanillin(Double.parseDouble(getXMLText(reader)));
                            break;
                        case NUTS:
                            ingredients.setNuts(Double.parseDouble(getXMLText(reader)));
                            break;
                        case CHOCOLATE:
                            ingredients.setChocolate(buildChocolate(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (CandyEnum.stringToEnum(name) == CandyEnum.INGREDIENTS) {
                        return ingredients;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Ingredients");
    }
    
    private Value buildValue(XMLStreamReader reader) throws XMLStreamException {
        Value value = new Value();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (CandyEnum.stringToEnum(name)) {
                        case PROTEINS:
                            value.setProteins(Double.parseDouble(getXMLText(reader)));
                            break;
                        case FATS:
                            value.setFats(Double.parseDouble(getXMLText(reader)));
                            break;
                        case CARBOHYDRATES:
                            value.setCarbohydrates(Double.parseDouble(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (CandyEnum.stringToEnum(name) == CandyEnum.VALUE) {
                        return value;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Value");
    }
    
    private Chocolate buildChocolate(XMLStreamReader reader) throws XMLStreamException {
        Chocolate chocolate = new Chocolate();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (CandyEnum.stringToEnum(name)) {
                        case CHOCOLATE_TYPE:
                            chocolate.setChocolateType(getXMLText(reader));
                            break;
                        case POROUS:
                            chocolate.setPorous(Boolean.valueOf(getXMLText(reader)));
                            break;
                        case AMOUNT:
                            chocolate.setAmount(Double.parseDouble(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (CandyEnum.stringToEnum(name) == CandyEnum.CHOCOLATE) {
                        return chocolate;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Value");
    }
    
    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}