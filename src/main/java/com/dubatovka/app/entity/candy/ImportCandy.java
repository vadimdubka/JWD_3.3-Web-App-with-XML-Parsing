package com.dubatovka.app.entity.candy;

public class ImportCandy extends Candy {

    private String countryCode;
    private String delivery;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String value) {
        this.countryCode = value;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String value) {
        this.delivery = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (o.getClass() != ImportCandy.class) return false;
        ImportCandy candy = (ImportCandy) o;

        if (isFilled() != candy.isFilled()) return false;
        if (getEnergy() != candy.getEnergy()) return false;
        if (getName() != null ? !getName().equals(candy.getName()) : candy.getName() != null) return false;
        if (getType() != null ? !getType().equals(candy.getType()) : candy.getType() != null) return false;
        if (getIngredients() != null ? !getIngredients().equals(candy.getIngredients()) : candy.getIngredients() != null)
            return false;
        if (getValue() != null ? !getValue().equals(candy.getValue()) : candy.getValue() != null) return false;
        if (countryCode != null ? !countryCode.equals(candy.countryCode) : candy.countryCode != null) return false;
        return delivery != null ? delivery.equals(candy.delivery) : candy.delivery == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (countryCode != null ? countryCode.hashCode() : 0);
        result = 31 * result + (delivery != null ? delivery.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ImportCandy[");
        sb.append(" countryCode = '").append(countryCode).append("' |");
        sb.append(" delivery = '").append(delivery).append("'");
        sb.append(" ]");
        sb.append(super.toString());
        return sb.toString();
    }
}