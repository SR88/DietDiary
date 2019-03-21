package com.shneddy.dietdiary.intermediates;
/**
 * Created By Seth Sneddon Mar 2019
 */
public class FoodAndTypeData {


    public int id;
    public String name;
    public double gramsSugar;
    public String type;
    public int foodTypeId;
    public String foodType;

    /**
     * This is utilized to create an intermediate or joining object class that can be utilized by
     * recycler list items
     */
    public FoodAndTypeData() {
    }

    /*
        GETTERS AND SETTERS BELOW
     */
    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getFoodTypeId() {
        return foodTypeId;
    }

    public void setFoodTypeId(int foodTypeId) {
        this.foodTypeId = foodTypeId;
    }

    @Override
    public String toString() {
        return "FoodAndTypeData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gramsSugar=" + gramsSugar +
                ", type='" + type + '\'' +
                ", foodTypeId=" + foodTypeId +
                ", foodType='" + foodType + '\'' +
                '}';
    }
}
