package com.dubatovka.app.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProducedCandy extends Candy {

    private Date shelfLife;
    private String producer;

    public Date getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(Date value) {
        this.shelfLife = value;
    }

    public String getProducer() {
        if (producer == null) {
            producer = CandyConstants.DEFAULT_PRODUCER;
        }
        return producer;
    }

    public void setProducer(String value) {
        this.producer = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (o.getClass() != ProducedCandy.class) return false;

        ProducedCandy candy = (ProducedCandy) o;
        if (isFilled() != candy.isFilled()) return false;
        if (getEnergy() != candy.getEnergy()) return false;
        if (getName() != null ? !getName().equals(candy.getName()) : candy.getName() != null) return false;
        if (getType() != null ? !getType().equals(candy.getType()) : candy.getType() != null) return false;
        if (getIngredients() != null ? !getIngredients().equals(candy.getIngredients()) : candy.getIngredients() != null)
            return false;
        if (getValue() != null ? !getValue().equals(candy.getValue()) : candy.getValue() != null) return false;
        if (shelfLife != null ? !shelfLife.equals(candy.shelfLife) : candy.shelfLife != null) return false;
        return producer != null ? producer.equals(candy.producer) : candy.producer == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (shelfLife != null ? shelfLife.hashCode() : 0);
        result = 31 * result + (producer != null ? producer.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.applyPattern(CandyConstants.DATE_FORMAT_PATTERN);
        String formattedShelfLife = dateFormat.format(shelfLife);
        final StringBuilder sb = new StringBuilder("ProducedCandy[");
        sb.append(" shelfLife = '").append(formattedShelfLife).append("' |");
        sb.append(" producer = '").append(producer).append("'");
        sb.append(" ]");
        sb.append(super.toString());
        return sb.toString();
    }
}