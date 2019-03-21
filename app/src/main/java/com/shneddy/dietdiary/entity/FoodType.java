package com.shneddy.dietdiary.entity;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
/**
 * Created By Seth Sneddon Feb 2019
 */
/**
 *  OBJECT RELATIONAL MAPPING ENTITY OF FOOD TYPE
 *
 *  THIS CLASS CONTAINS GETTERS AND SETTERS
 *
 *  THIS CLASS EXHIBITS DATA ENCAPSULATIONS/HIDING
 */
@Entity(tableName = "food_type", indices = {@Index("id")})
public class FoodType {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String type;
    private String description;

    /**
     * This constructor is used by the engineer and Room to create, update, and delete records
     * in the sqlite database.
     *
     * @param type string value of what the category actually is
     * @param description for the user to put in clarification if they wish
     */
    public FoodType(String type, String description) {
        this.type = type;
        this.description = description;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return type;
    }
}
