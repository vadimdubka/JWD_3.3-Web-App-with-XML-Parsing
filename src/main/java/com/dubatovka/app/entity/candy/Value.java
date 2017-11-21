package com.dubatovka.app.entity.candy;

public class Value {

    private double proteins;
    private double fats;
    private double carbohydrates;

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double value) {
        this.proteins = value;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double value) {
        this.fats = value;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double value) {
        this.carbohydrates = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (o.getClass() != Value.class) return false;

        Value value = (Value) o;
        if (Double.compare(value.proteins, proteins) != 0) return false;
        if (Double.compare(value.fats, fats) != 0) return false;
        return Double.compare(value.carbohydrates, carbohydrates) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(proteins);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(fats);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(carbohydrates);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{\n");
        sb.append("\t\t\tproteins = '").append(proteins).append("'\n");
        sb.append("\t\t\tfats = '").append(fats).append("'\n");
        sb.append("\t\t\tcarbohydrates = '").append(carbohydrates).append("'\n");
        sb.append("\t\t}");
        return sb.toString();
    }
}