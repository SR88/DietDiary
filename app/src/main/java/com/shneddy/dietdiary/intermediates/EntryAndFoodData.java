package com.shneddy.dietdiary.intermediates;
/**
 * Created By Seth Sneddon Mar 2019
 */
public class EntryAndFoodData {

    private int id;
    private int foodId;
    private String name;
    private double gramsSugar;
    private String type;
    private double portionSize;
    private String date;
    private double calcSugars;
    private int entryId;

    public int getEntryId() {
        return entryId;
    }

    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGramsSugar() {
        return gramsSugar;
    }

    public void setGramsSugar(double gramsSugar) {
        this.gramsSugar = gramsSugar;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPortionSize() {
        return portionSize;
    }

    public void setPortionSize(double portionSize) {
        this.portionSize = portionSize;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getCalcSugars() {
        return calcSugars;
    }

    public void setCalcSugars(double calcSugars) {
        this.calcSugars = calcSugars;
    }

    @Override
    public String toString() {
        return "EntryAndFoodData{" +
                "id=" + id +
                ", foodId=" + foodId +
                ", name='" + name + '\'' +
                ", gramsSugar=" + gramsSugar +
                ", type='" + type + '\'' +
                ", portionSize=" + portionSize +
                ", date='" + date + '\'' +
                ", calcSugars=" + calcSugars +
                ", entryId=" + entryId +
                '}';
    }
}
