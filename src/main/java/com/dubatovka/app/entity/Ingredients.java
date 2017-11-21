package com.dubatovka.app.entity;

public class Ingredients {

    private double water;
    private double sugar;
    private double milk;
    private double fructose;
    private double vanillin;
    private Chocolate chocolate;
    private double nuts;

    public double getWater() {
        return water;
    }

    public void setWater(double value) {
        this.water = value;
    }

    public double getSugar() {
        return sugar;
    }

    public void setSugar(double value) {
        this.sugar = value;
    }

    public double getMilk() {
        return milk;
    }

    public void setMilk(double value) {
        this.milk = value;
    }

    public double getFructose() {
        return fructose;
    }

    public void setFructose(double value) {
        this.fructose = value;
    }

    public double getVanillin() {
        return vanillin;
    }

    public void setVanillin(double value) {
        this.vanillin = value;
    }

    public Chocolate getChocolate() {
        if (chocolate == null) {
            chocolate = new Chocolate();
        }
        return chocolate;
    }

    public void setChocolate(Chocolate value) {
        this.chocolate = value;
    }

    public double getNuts() {
        return nuts;
    }

    public void setNuts(double value) {
        this.nuts = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (o.getClass() != Ingredients.class) return false;

        Ingredients that = (Ingredients) o;
        if (Double.compare(that.water, water) != 0) return false;
        if (Double.compare(that.sugar, sugar) != 0) return false;
        if (Double.compare(that.milk, milk) != 0) return false;
        if (Double.compare(that.fructose, fructose) != 0) return false;
        if (Double.compare(that.vanillin, vanillin) != 0) return false;
        if (Double.compare(that.nuts, nuts) != 0) return false;
        return chocolate != null ? chocolate.equals(that.chocolate) : that.chocolate == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(water);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(sugar);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(milk);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(fructose);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(vanillin);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (chocolate != null ? chocolate.hashCode() : 0);
        temp = Double.doubleToLongBits(nuts);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{\n");
        sb.append("\t\t\twater = '").append(water).append("'\n");
        sb.append("\t\t\tsugar = '").append(sugar).append("'\n");
        sb.append("\t\t\tmilk = '").append(milk).append("'\n");
        sb.append("\t\t\tfructose = '").append(fructose).append("'\n");
        sb.append("\t\t\tvanillin = '").append(vanillin).append("'\n");
        sb.append("\t\t\tchocolate = '").append(chocolate).append("'\n");
        sb.append("\t\t\tnuts = '").append(nuts).append("'\n");
        sb.append("\t\t}");
        return sb.toString();
    }
}