package com.dubatovka.app.dao.candybuilder;

import com.dubatovka.app.entity.Candy;
import com.dubatovka.app.entity.CandyConstants;
import com.dubatovka.app.entity.Chocolate;
import com.dubatovka.app.entity.ImportCandy;
import com.dubatovka.app.entity.Ingredients;
import com.dubatovka.app.entity.ProducedCandy;
import com.dubatovka.app.entity.Value;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class CandiesBuilderTest {
    private static final String TEST_XML_PATH = "data/candies_test.xml";
    private static final String TEST_XSD_PATH = "data/candies_test.xsd";
    
    private static ProducedCandy producedCandy;
    private static ImportCandy importCandy;
    
    @BeforeClass
    public static void init() throws ParseException {
        producedCandy = new ProducedCandy();
        producedCandy.setName("Toptishka");
        producedCandy.setType("chocolate");
        producedCandy.setFilled(false);
        producedCandy.setEnergy(488);
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.applyPattern(CandyConstants.DATE_FORMAT_PATTERN);
        producedCandy.setShelfLife(dateFormat.parse("2017-09-24"));
        producedCandy.setProducer(CandyConstants.DEFAULT_PRODUCER);
        
        Ingredients pIngredients = new Ingredients();
        pIngredients.setWater(20.2);
        pIngredients.setSugar(10.8);
        pIngredients.setMilk(15.0);
        pIngredients.setFructose(2.5);
        pIngredients.setVanillin(3.7);
        pIngredients.setNuts(0);
        
        Chocolate pChocolate = new Chocolate();
        pChocolate.setChocolateType("dark");
        pChocolate.setPorous(false);
        pChocolate.setAmount(20);
        pIngredients.setChocolate(pChocolate);
        
        producedCandy.setIngredients(pIngredients);
        
        Value pValue = new Value();
        pValue.setProteins(17.2);
        pValue.setFats(34.7);
        pValue.setCarbohydrates(51.0);
        
        producedCandy.setValue(pValue);
        
        importCandy = new ImportCandy();
        importCandy.setName("Snickers");
        importCandy.setType("mixed");
        importCandy.setFilled(true);
        importCandy.setEnergy(684);
        importCandy.setDelivery("A173621");
        importCandy.setCountryCode("RUS");
        
        Ingredients iIngredients = new Ingredients();
        iIngredients.setWater(0);
        iIngredients.setSugar(1.4);
        iIngredients.setMilk(22.2);
        iIngredients.setFructose(0);
        iIngredients.setVanillin(0);
        iIngredients.setNuts(24.4);
        
        Chocolate iChocolate = new Chocolate();
        iChocolate.setChocolateType("dark");
        iChocolate.setPorous(false);
        iChocolate.setAmount(20);
        iIngredients.setChocolate(iChocolate);
        
        importCandy.setIngredients(iIngredients);
        
        Value iValue = new Value();
        iValue.setProteins(17.7);
        iValue.setFats(28.8);
        iValue.setCarbohydrates(39.9);
        
        importCandy.setValue(iValue);
    }
    
    @AfterClass
    public static void destroy() {
        producedCandy = null;
        importCandy = null;
    }
    
    @Test
    public void checkDOMBuilder() {
        CandyBuilderDirector candyBuilderDirector = new CandyBuilderDirector("dom");
        List<Candy> candySet = candyBuilderDirector.constructCandyList(TEST_XML_PATH, TEST_XSD_PATH);
        boolean equals = checkEquals(candySet);
        Assert.assertTrue("Built objects are not equals to expected.", equals);
    }
    
    @Test
    public void checkSAXBuilder() {
        CandyBuilderDirector candyBuilderDirector = new CandyBuilderDirector("dom");
        List<Candy> candySet = candyBuilderDirector.constructCandyList(TEST_XML_PATH, TEST_XSD_PATH);
        boolean equals = checkEquals(candySet);
        Assert.assertTrue("Built objects are not equals to expected.", equals);
    }
    
    @Test
    public void checkStAXBuilder() {
        CandyBuilderDirector candyBuilderDirector = new CandyBuilderDirector("dom");
        List<Candy> candySet = candyBuilderDirector.constructCandyList(TEST_XML_PATH, TEST_XSD_PATH);
        boolean equals = checkEquals(candySet);
        Assert.assertTrue("Built objects are not equals to expected.", equals);
    }
    
    private boolean checkEquals(List<Candy> candySet) {
        boolean equals = true;
        for (Candy candy : candySet) {
            if (!(importCandy.equals(candy) || producedCandy.equals(candy))) {
                equals = false;
            }
        }
        return equals;
    }
}