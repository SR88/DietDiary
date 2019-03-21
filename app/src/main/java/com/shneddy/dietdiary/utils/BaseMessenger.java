package com.shneddy.dietdiary.utils;
/**
 * Created By Seth Sneddon Mar 2019
 */

/**
 * Class utilized for demonstration of polymorphism
 */
public class BaseMessenger {

    private String message = "There is a conflict!";

    /**
     * Gets message value for exception control for validation
     * @return message string for exception popup
     */
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
