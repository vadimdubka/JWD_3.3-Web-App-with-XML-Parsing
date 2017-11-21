package com.dubatovka.app.dao.candybuilder;

import com.dubatovka.app.entity.candy.Candy;
import com.dubatovka.app.entity.candy.CandyConstants;
import com.dubatovka.app.entity.candy.ImportCandy;
import com.dubatovka.app.entity.candy.ProducedCandy;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CandiesSAXHandler extends DefaultHandler {
    private final Logger logger = LogManager.getLogger(CandiesSAXHandler.class);
    private List<Candy> candies;
    private Candy current;
    private CandyEnum currentEnum;
    private EnumSet<CandyEnum> withText;

    public CandiesSAXHandler() {
        candies = new ArrayList<>();
        withText = EnumSet.range(CandyEnum.NAME, CandyEnum.COUNTRY_CODE);
    }

    public List<Candy> getCandies() {
        return candies;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (CandyEnum.PRODUCED_CANDY.getValue().equals(localName)) {
            ProducedCandy candy = new ProducedCandy();
            for (int i = 0; i < attributes.getLength(); i++) {
                if (CandyEnum.SHELF_LIFE.getValue().equals(attributes.getLocalName(i))) {
                    SimpleDateFormat format = new SimpleDateFormat();
                    format.applyPattern(CandyConstants.DATE_FORMAT_PATTERN);
                    try {
                        Date shelfLife = format.parse(attributes.getValue(i));
                        candy.setShelfLife(shelfLife);
                    } catch (ParseException e) {
                        logger.log(Level.ERROR, "ParseException while parsing SHELF_LIFE in startElement method of CandiesSAXHandler: " + e);
                    }
                }
                if (CandyEnum.PRODUCER.getValue().equals(attributes.getLocalName(i))) {
                    candy.setProducer(attributes.getValue(i));
                }
            }
            candy.getProducer();
            current = candy;
        } else if (CandyEnum.IMPORT_CANDY.getValue().equals(localName)) {
            ImportCandy candy = new ImportCandy();
            candy.setDelivery(attributes.getValue(0));
            current = candy;
        } else {
            CandyEnum temp = CandyEnum.stringToEnum(localName);
            if (withText.contains(temp)) {
                currentEnum = temp;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (CandyEnum.PRODUCED_CANDY.getValue().equals(localName)
                || CandyEnum.IMPORT_CANDY.getValue().equals(localName)) {
            candies.add(current);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case NAME:
                    current.setName(s);
                    break;
                case TYPE:
                    current.setType(s);
                    break;
                case FILLED:
                    current.setFilled(Boolean.valueOf(s));
                    break;
                case ENERGY:
                    current.setEnergy(Integer.parseInt(s));
                    break;
                case WATER:
                    current.getIngredients().setWater(Double.parseDouble(s));
                    break;
                case SUGAR:
                    current.getIngredients().setSugar(Double.parseDouble(s));
                    break;
                case MILK:
                    current.getIngredients().setMilk(Double.parseDouble(s));
                    break;
                case FRUCTOSE:
                    current.getIngredients().setFructose(Double.parseDouble(s));
                    break;
                case VANILLIN:
                    current.getIngredients().setVanillin(Double.parseDouble(s));
                    break;
                case NUTS:
                    current.getIngredients().setNuts(Double.parseDouble(s));
                    break;
                case CHOCOLATE_TYPE:
                    current.getIngredients().getChocolate().setChocolateType(s);
                    break;
                case POROUS:
                    current.getIngredients().getChocolate().setPorous(Boolean.valueOf(s));
                    break;
                case AMOUNT:
                    current.getIngredients().getChocolate().setAmount(Double.parseDouble(s));
                    break;
                case PROTEINS:
                    current.getValue().setProteins(Double.parseDouble(s));
                    break;
                case FATS:
                    current.getValue().setFats(Double.parseDouble(s));
                    break;
                case CARBOHYDRATES:
                    current.getValue().setCarbohydrates(Double.parseDouble(s));
                    break;
                case COUNTRY_CODE:
                    ((ImportCandy) current).setCountryCode(s);
                    break;
                default:
                    throw new EnumConstantNotPresentException(currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }
}