package com.shneddy.dietdiary.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Created By Seth Sneddon Mar 2019
 */
/**
 *  OBJECT RELATIONAL MAPPING ENTITY OF FOOD
 *
 *  THIS CLASS CONTAINS GETTERS AND SETTERS
 *
 *  THIS CLASS EXHIBITS DATA ENCAPSULATIONS/HIDING
 */
@Entity(
        foreignKeys =
                @ForeignKey(entity = FoodType.class,
                        parentColumns = "id",
                        childColumns = "foodTypeId",
                        onDelete = CASCADE),
        indices = {@Index("foodTypeId"), @Index("id")})
public class Food {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    private String name;
    @NonNull
    private double gramsSugar;
    @NonNull
    private int foodTypeId;

    /**
     * This constructor is used by the engineer and Room to create, update, and delete records
     * in the sqlite database.
     *
     * @param name of the food inserted
     * @param gramsSugar in the food being inserted
     * @param foodTypeId foreign key of food type this food falls under
     */
    public Food(String name, @NonNull double gramsSugar, @NonNull int foodTypeId) {
        this.name = name;
        this.gramsSugar = gramsSugar;
        this.foodTypeId = foodTypeId;
    }

    /*
        GETTERS AND SETTERS BELOW
    */
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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

    public void setFoodTypeId(int foodTypeId) {
        this.foodTypeId = foodTypeId;
    }

    public String getName() {
        return name;
    }

    public int getFoodTypeId() {
        return foodTypeId;
    }

    public Food getFood(){
        return this;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gramsSugar=" + gramsSugar +
                ", foodTypeId=" + foodTypeId +
                '}';
    }
}
