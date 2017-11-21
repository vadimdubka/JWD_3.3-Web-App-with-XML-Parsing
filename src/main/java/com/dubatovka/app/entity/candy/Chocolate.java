package com.dubatovka.app.entity.candy;

public class Chocolate {

    private String chocolateType;
    private boolean porous;
    private double amount;

    public String getChocolateType() {
        return chocolateType;
    }

    public void setChocolateType(String value) {
        this.chocolateType = value;
    }

    public boolean isPorous() {
        return porous;
    }

    public void setPorous(boolean value) {
        this.porous = value;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double value) {
        this.amount = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (o.getClass() != Chocolate.class) return false;

        Chocolate chocolate = (Chocolate) o;
        if (porous != chocolate.porous) return false;
        if (Double.compare(chocolate.amount, amount) != 0) return false;
        return chocolateType != null ? chocolateType.equals(chocolate.chocolateType) : chocolate.chocolateType == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = chocolateType != null ? chocolateType.hashCode() : 0;
        result = 31 * result + (porous ? 1 : 0);
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Chocolate{" +
                "chocolateType='" + chocolateType + '\'' +
                ", porous=" + porous +
                ", amount=" + amount +
                '}';
    }
}