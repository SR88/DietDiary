package com.shneddy.dietdiary.utils;
/**
 * Created By Seth Sneddon Mar 2019
 */

/**
 * Class utilized for demonstration of polymorphism
 */
public class FoodMessenger extends BaseMessenger {

    private String message = "No previously established Food Types!";

    /**
     * Gets message value for exception control for validation
     * @return message string for exception popup
     */
    @Override
    public String getMessage() {
        return message;
    }
}
