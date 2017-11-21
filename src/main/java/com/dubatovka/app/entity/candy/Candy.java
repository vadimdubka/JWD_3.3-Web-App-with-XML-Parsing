package com.dubatovka.app.entity.candy;

public class Candy {

    private String name;
    private String type;
    private boolean filled;
    private Ingredients ingredients;
    private Value value;
    private int energy;

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type = value;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean value) {
        this.filled = value;
    }

    public Ingredients getIngredients() {
        if (ingredients == null) {
            ingredients = new Ingredients();
        }
        return ingredients;
    }

    public void setIngredients(Ingredients value) {
        this.ingredients = value;
    }

    public Value getValue() {
        if (value == null) {
            value = new Value();
        }
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int value) {
        this.energy = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (o.getClass() != Candy.class) return false;

        Candy candy = (Candy) o;
        if (filled != candy.filled) return false;
        if (energy != candy.energy) return false;
        if (name != null ? !name.equals(candy.name) : candy.name != null) return false;
        if (type != null ? !type.equals(candy.type) : candy.type != null) return false;
        if (ingredients != null ? !ingredients.equals(candy.ingredients) : candy.ingredients != null) return false;
        return value != null ? value.equals(candy.value) : candy.value == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (filled ? 1 : 0);
        result = 31 * result + (ingredients != null ? ingredients.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + energy;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" {\n");
        sb.append("\t\tname = '").append(name).append("'\n");
        sb.append("\t\ttype = '").append(type).append("'\n");
        sb.append("\t\tfilled = '").append(filled).append("'\n");
        sb.append("\t\tingredients = ").append(ingredients).append("\n");
        sb.append("\t\tvalue = ").append(value).append("\n");
        sb.append("\t\tenergy = '").append(energy).append("'\n");
        sb.append("}\n");
        return sb.toString();
    }
}